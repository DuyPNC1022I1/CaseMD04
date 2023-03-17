package code.gym.webmediaplayer.controller;

import code.gym.webmediaplayer.model.*;
import code.gym.webmediaplayer.service.Icrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {

    @ModelAttribute("singers")
    public Iterable<Singer> singers() {
        return singerService.findALl();
    }

    @ModelAttribute("albums")
    public Iterable<Album> albums() {
        return albumService.findALl();
    }

    @Value("${imageSong}")
    private String imagePath;

    @Value("${media}")
    private String fileSongPath;

    @Autowired
    private Icrud<Song> songService;

    @Autowired
    private Icrud<Album> albumService;

    @Autowired
    private Icrud<Singer> singerService;

    @Autowired
    private Icrud<Account> accountService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("songs", songService.findALl());
        model.addAttribute("singer", singerService.findALl());
        model.addAttribute("album", albumService.findALl());
        model.addAttribute("songsByDateESC", songService.findAllOrderByLocalDateDesc());
        return "HomePage";
    }

    //Phan xu ly dieu huong Login
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("account", new Account());
        return "login";
    }

    @PostMapping("/checkLogin")
    public String resultLogin(@ModelAttribute("account") Account account, Model model, Pageable pageable) {
        List<Account> accounts = (List<Account>) accountService.findALl();
        Boolean block = true;
        String admin = "admin@gmail.com";
        String passAdmin = "123";
        for (Account a : accounts) {
            if (account.getEmail().equals(a.getEmail())) {
                account.setBlock(a.getBlock());
                break;
            } else {
                account.setBlock(false);
            }
        }
        for (Account a : accounts) {
            if (a.getEmail().equals(account.getEmail()) && a.getPassword().equals(account.getPassword()) && block.equals(account.getBlock())) {
                return "redirect:/songs/userHome";
            } else if (admin.toUpperCase().equals(account.getEmail().toUpperCase())
                    && passAdmin.toUpperCase().equals(account.getPassword().toUpperCase())) {
                return "redirect:/songs/adminHome";
            }
        }
        model.addAttribute("message", "Login Fail! Re-Enter your information");
        return "login";
    }


    @GetMapping("/adminHome")
    public String showAdminHome(Model model, @PageableDefault(value = 4) Pageable pageable) {
        model.addAttribute("songs", songService.findALl(pageable));
        return "adminHome";
    }

    //Manger user
    @GetMapping("/managerUser")
    public String managerUser(Model model) {
        model.addAttribute("users", accountService.findALl());
        return "userManager";
    }

    //Block user
    @GetMapping("/blockUser/{id}")
    public String blockUser(@PathVariable("id") Long id, Model model) {
        Account account = accountService.finById(id).get();
        account.setBlock(false);
        accountService.save(account);
        return "redirect:/songs/managerUser";
    }

    @GetMapping("/unlockUser/{id}")
    public String unlockUser(@PathVariable("id") Long id, Model model) {
        Account account = accountService.finById(id).get();
        account.setBlock(true);
        accountService.save(account);
        return "redirect:/songs/managerUser";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        accountService.delete(id);
        return "redirect:/songs/managerUser";
    }

    @GetMapping("/createUser")
    public String createUser(Model model) {
        Account account = new Account();
        account.setBlock(true);
        model.addAttribute("user", account);
        return "registerForm";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") Account account, Model model) {
        try {
            model.addAttribute("message", "Complete register!!!");
            accountService.save(account);
        } catch (ConstraintViolationException e) {
            model.addAttribute("message", "Register Fail! Please your data format!!!");
            return "registerForm";
        }

        return "redirect:/songs/login";
    }

    //Phan dieu huong user
    @GetMapping("/userHome")
    public String showUserHome(Model model, @PageableDefault(value = 4) Pageable pageable) {
        model.addAttribute("songs", songService.findALl(pageable));
        return "userHome";
    }

    @GetMapping("/findNameByUser")
    public String findNameByUser(Model model, @RequestParam("search") String name, Pageable pageable) {
        Page<Song> songs = songService.findAllByNameContaining(name, pageable);
        model.addAttribute("songs", songs);
        return "userHome";
    }

    @GetMapping("/createByUser")
    public String createByUser(Model model) {
        SongForm songForm = new SongForm();
        model.addAttribute("songForm", songForm);
        model.addAttribute("singers", singerService.findALl());
        model.addAttribute("albums", albumService.findALl());
        return "createFormByUser";
    }

    @PostMapping("/saveByUser")
    public String saveByUser(@ModelAttribute("songForm") SongForm sf, BindingResult bindingResult, Model model, Pageable pageable){
        if (bindingResult.hasErrors()) {
            return "createFormByUser";
        }
        //Avatar
        MultipartFile fileUpload1 = sf.getAvatar();
        String avatarPath = fileUpload1.getOriginalFilename();
        try {
            FileCopyUtils.copy(fileUpload1.getBytes(), new File(imagePath + avatarPath));
        }catch (IOException e){
        model.addAttribute("message", "Fail to create! Re-enter information to create new!");
        return "createForm";
        }
        //File Mp3
        MultipartFile fileUpload2 = sf.getSong();
        String songPath = fileUpload2.getOriginalFilename();
        try {
            FileCopyUtils.copy(fileUpload2.getBytes(), new File(fileSongPath + songPath));
        } catch (IOException e) {
            model.addAttribute("message", "Fail to create! Re-enter information to create new!");
            return "createForm";
        }

        // save song here...
        Song song = new Song(sf.getId(), sf.getName(), sf.getPrice(), java.time.LocalDate.now(), sf.getDescription(), sf.getSinger(), sf.getAlbum(), avatarPath, songPath);
        try {
            songService.save(song);
        } catch (ConstraintViolationException e) {
            model.addAttribute("message", "Fail to create! Re-enter information to create new!");
            return "createFormByUser";
        }
        model.addAttribute("songs", songService.findALl(pageable));
        return "redirect:/songs/userHome";
    }

    //Phan xu ly edit song
    @GetMapping("/create")
    public String create(Model model) {
        SongForm songForm = new SongForm();
        model.addAttribute("songForm", songForm);
        model.addAttribute("singers", singerService.findALl());
        model.addAttribute("albums", albumService.findALl());
        return "createForm";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("songForm") SongForm sf, BindingResult bindingResult, Model model, Pageable pageable) {
        if (bindingResult.hasErrors()) {
            return "createForm";
        }
        //Avatar
        MultipartFile fileUpload1 = sf.getAvatar();
        String avatarPath = fileUpload1.getOriginalFilename();
        try {
            FileCopyUtils.copy(fileUpload1.getBytes(), new File(imagePath + avatarPath));
        } catch (IOException e) {
            model.addAttribute("message", "Fail to create! Re-enter information to create new!");
            return "createForm";
        }
        //File Mp3
        MultipartFile fileUpload2 = sf.getSong();
        String songPath = fileUpload2.getOriginalFilename();
        try {
            FileCopyUtils.copy(fileUpload2.getBytes(), new File(fileSongPath + songPath));
        } catch (IOException e){
            model.addAttribute("message", "Fail to create! Re-enter information to create new!");
            return "createForm";
        }
        // save song here...
        Song song = new Song(sf.getId(), sf.getName(), sf.getPrice(), java.time.LocalDate.now(), sf.getDescription(), sf.getSinger(), sf.getAlbum(), avatarPath, songPath);
        songService.save(song);
        model.addAttribute("songs", songService.findALl(pageable));
        return "redirect:/songs/adminHome";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        Song song = songService.finById(id).get();
        double price = Double.parseDouble(String.valueOf(song.getPrice()));
        SongForm s = new SongForm(id, song.getName(), price, song.getDescription(), song.getSinger(), song.getAlbum());
        model.addAttribute("songForm", s);
        return "createForm";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        songService.delete(id);
        return "redirect:/songs/adminHome";
    }

    @GetMapping("/findByName")
    public String findByName(Model model, @RequestParam("search") String name, Pageable pageable) {
        Page<Song> songs = songService.findAllByNameContaining(name, pageable);
        model.addAttribute("songs", songs);
        return "adminHome";
    }

    //HandlerException
    @ExceptionHandler(IOException.class)
    public ModelAndView pageFail() {
        return new ModelAndView("err");
    }
}
