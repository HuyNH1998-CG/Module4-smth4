package bigg.controller;

import bigg.model.Classroom;
import bigg.model.Student;
import bigg.service.IClassroomService;
import bigg.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

@Controller
public class StudentController {
    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    IClassroomService classroomService;

    @Autowired
    IStudentService studentService;

    @ModelAttribute("classroom")
    public Iterable<Classroom> classrooms(){
        return classroomService.findAll();
    }

    @GetMapping("/")
    public ModelAndView home(@PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC)Pageable pageable){
        Page<Student> students = studentService.findAllPage(pageable);
        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate(){
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@RequestParam MultipartFile file, @ModelAttribute Student student){
        getFile(file, student);
        studentService.save(student);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable long id){
        Student student = studentService.finById(id).get();
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("student",student);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@RequestParam MultipartFile file,@ModelAttribute Student student){
        getFile(file, student);
        studentService.save(student);
        return new ModelAndView("redirect:/");
    }

    private void getFile(@RequestParam MultipartFile file, @ModelAttribute Student student) {
        String image = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload, image));
        } catch (Exception e) {
            e.printStackTrace();
        }
        student.setImage(image);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView confirmDelete(@PathVariable long id){
        Student student = studentService.finById(id).get();
        ModelAndView modelAndView = new ModelAndView("/delete");
        modelAndView.addObject("student",student);
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteCustomer(@ModelAttribute Student student) {
        studentService.remove(student.getId());
        return new ModelAndView("redirect:/");
    }
}
