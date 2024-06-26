package com.telusko.Job.controller;

import com.telusko.Job.service.JobService;
import com.telusko.Job.dto.JobDTO;
import com.telusko.Job.model.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    private final JobService jobService;


    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/Jobs")
    public ResponseEntity<List<JobDTO>> findAll() {
        return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
    }


    @PostMapping("/Job")
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job Added Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/Job/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Integer id) {
        JobDTO jobDTO = jobService.getJobById(id);
        if (jobDTO != null) {
            return new ResponseEntity<>(jobDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/Job/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Integer id) {
        if (jobService.deleteJobById(id)) {
            return new ResponseEntity<>("Job Deleted Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Job Id not found", HttpStatus.OK);
        }
    }


    @PutMapping("/Job/{id}")
    public ResponseEntity<Job> updateJobById(@PathVariable Integer id, @RequestBody Job updatedJob) {
        Job job = jobService.updatedJobById(id, updatedJob);
        if (job != null) {
            return new ResponseEntity<>(job, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}



