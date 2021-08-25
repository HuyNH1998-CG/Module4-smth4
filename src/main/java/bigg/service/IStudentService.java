package bigg.service;

import bigg.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudentService extends IGeneralService<Student> {
    Page<Student> findAllPage(Pageable pageable);
}
