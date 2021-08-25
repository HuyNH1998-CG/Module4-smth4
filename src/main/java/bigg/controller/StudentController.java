package bigg.controller;

import bigg.model.Classroom;
import bigg.model.Student;
import bigg.service.IClassroomService;
import bigg.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

    @Autowired
    IClassroomService classroomService;

    @Autowired
    IStudentService studentService;

    @ModelAttribute("classroom")
    public Iterable<Classroom> classrooms(){
        return classroomService.findAll();
    }

    @GetMapping("/home")
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
    public ModelAndView create(@ModelAttribute Student student){
        studentService.save(student);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable long id){
        Student student = studentService.finById(id).get();
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("student",student);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@ModelAttribute Student student){
        studentService.save(student);
        return new ModelAndView("redirect:/home");
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
        return new ModelAndView("redirect:/home");
    }
}
