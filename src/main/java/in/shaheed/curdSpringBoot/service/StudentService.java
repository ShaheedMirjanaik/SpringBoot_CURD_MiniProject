package in.shaheed.curdSpringBoot.service;

import in.shaheed.curdSpringBoot.entity.Student;
import in.shaheed.curdSpringBoot.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    //1. End point listen(/app/student port)
    //2. Business logic
    //3, interact with DB to store
    //4. Response back to client(postman)
    // postman -> Student Controller -> Student Service -> Student Repository -> DB
    // Controller - Service - Repository

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq) {
        //business login
        //store to db
        Student studentResp = studentRepository.save(studentReq);
        return  studentResp;
    }

    public Student getStudent(Long id){
        Optional<Student> studentResp = studentRepository.findById(id);
        if(studentResp.isPresent()){
            return studentResp.get();
        }
        return null;
    }

    public List<Student> getAllStudents(){
        List<Student> studentListResp = studentRepository.findAll();
        return studentListResp;
    }

    public Student updateStudent(Long id, Student studentReq){
        Optional<Student> existingStudent = studentRepository.findById(id);
        if(existingStudent.isEmpty()){
            return null;
        }
        Student studentToSave = existingStudent.get();
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setName(studentReq.getName());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setRollNo(studentReq.getRollNo());
        studentToSave.setCourse_id(studentReq.getCourse_id());
        return studentRepository.save(studentToSave);
    }

    public Boolean deleteStudent(Long id){
        Boolean isStudent = studentRepository.existsById(id);
        if(isStudent){
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

}
