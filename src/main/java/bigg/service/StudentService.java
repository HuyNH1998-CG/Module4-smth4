package bigg.service;

import bigg.model.Student;
import bigg.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class StudentService implements IStudentService {

    @Autowired
    IStudentRepository studentRepository;

    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> finById(long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void remove(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Page<Student> findAllPage(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
}
