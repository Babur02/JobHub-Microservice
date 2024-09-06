package com.babar.jobmicroservice.job;

import java.util.List;

import com.babar.jobmicroservice.job.dto.JobWithCompanyDTO;

public interface JobService {

    List<JobWithCompanyDTO> findJobs();

    void createJob(Job job);

    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
