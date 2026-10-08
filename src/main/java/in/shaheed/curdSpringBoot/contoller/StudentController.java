package in.shaheed.curdSpringBoot.contoller;

import in.shaheed.curdSpringBoot.entity.Student;
import in.shaheed.curdSpringBoot.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // create Student - POST - /api/students
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student createStudent = studentService.createStudent(student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createStudent);
    }

    //Read Student - GET - /api/students/{id} or /api/students
    @GetMapping()
    public ResponseEntity<Student> getStudent(@RequestParam Long id){
        Student studentResp = studentService.getStudent(id);
        if(studentResp == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentResp);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> studentListResp  = studentService.getAllStudents();
        if(studentListResp == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentListResp);
    }

    //Update Student - PUT - /api/students/{id}
    @PutMapping()
    public ResponseEntity<Student> updateStudent(@RequestParam Long id, @RequestBody Student studentReq){
        Student studentResp = studentService.updateStudent(id, studentReq);
        if(studentResp == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentResp);
    }

    // Delete Student - DELETE - /api/students/{id}
    @DeleteMapping()
    public ResponseEntity<String> deleteStudent(@RequestParam Long id){
        Boolean isDeleted = studentService.deleteStudent(id);
        if(!isDeleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Records deleted successfully.");
    }

    @PatchMapping("/delete-soft/")
    public ResponseEntity<String> deleteStudentSoftly(@RequestParam Long id){
        Boolean isDeleted = studentService.deleteStudentSoftly(id);
        if(!isDeleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Records deleted successfully.");
    }
}
