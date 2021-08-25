package bigg.repository;

import bigg.model.Classroom;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IClassroomRepository extends PagingAndSortingRepository<Classroom, Long> {
}
