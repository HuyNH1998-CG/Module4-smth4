package bigg.service;

import bigg.model.Classroom;
import bigg.repository.IClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ClassroomService implements IClassroomService{
    @Autowired
    IClassroomRepository classroomRepository;
    @Override
    public Iterable<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    @Override
    public Optional<Classroom> finById(long id) {
        return classroomRepository.findById(id);
    }

    @Override
    public void save(Classroom classroom) {
        classroomRepository.save(classroom);
    }

    @Override
    public void remove(long id) {
        classroomRepository.deleteById(id);
    }
}
