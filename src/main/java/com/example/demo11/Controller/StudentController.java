package com.example.demo11.Controller;

import com.example.demo11.Model.Response.EntityResponse;
import com.example.demo11.Model.SaveRequest.SaveStudentRequest;
import com.example.demo11.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api1")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    @GetMapping("/firstapi")
    public String firstapi(){return "Working";}

    @PostMapping("/saveOrUpdateStudent")
    public ResponseEntity<?> saveOrUpdateStudent(@RequestBody SaveStudentRequest saveStudentRequest){
        return new ResponseEntity<>(studentService.saveOrUpdateStudent(saveStudentRequest), HttpStatus.OK);
    }
    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam Long studentId) {
        try {
            return new ResponseEntity<>(new EntityResponse(studentService.getById(studentId), 0), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(new EntityResponse(e.getMessage(), -1), HttpStatus.NOT_FOUND);
        }
        }


    @RequestMapping(value = "/getAllStudent",method = RequestMethod.GET)
    private ResponseEntity<?> getAllStudent(@RequestParam(defaultValue ="0",required = false) Integer pageNo,
                                            @RequestParam(defaultValue="30",required = false) Integer pageSize,
                                            @RequestParam(required = false) String studentName,
                                            @RequestParam(required = false)String studentEmail,
                                            @RequestParam(required = false)String studentMobileNo){
        try{
            Pageable pageable= PageRequest.of(pageNo,pageSize);
        return new ResponseEntity<>(new EntityResponse(studentService.getAllByDeleted(studentName,studentEmail,studentMobileNo,pageable),0),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new EntityResponse(e.getMessage(),-1),HttpStatus.OK);
        }
        }
    @DeleteMapping("/deleteById/{studentId}")
    public ResponseEntity<?> deleteById(@PathVariable Long studentId) throws Exception {
        try{
            return  new ResponseEntity<>(new EntityResponse(studentService.deleteById(studentId),0),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new EntityResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }
    @PutMapping("/changeStatus")
    public ResponseEntity<?> changeStatus(@RequestParam Long studentId){
        try {
            return new ResponseEntity<>(new EntityResponse(studentService.changestatus(studentId), 0), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new EntityResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }

}
