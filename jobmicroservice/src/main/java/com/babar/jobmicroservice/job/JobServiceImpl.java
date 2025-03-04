package com.babar.jobmicroservice.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.babar.jobmicroservice.job.dto.JobWithCompanyDTO;
import com.babar.jobmicroservice.job.external.Company;

@Service
public class JobServiceImpl implements JobService {
    // private List<Job> jobs = new ArrayList<>();
    @Autowired
    JobRepository jobRepository;

    @Override
    public List<JobWithCompanyDTO> findJobs() {
        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOs = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        for (Job job : jobs) {
            JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
            jobWithCompanyDTO.setJob(job);
            Company company = restTemplate.getForObject("http://localhost:8082/companies/" + job.getCompanyId(),
                    Company.class);
            jobWithCompanyDTO.setCompany(company);
            jobWithCompanyDTOs.add(jobWithCompanyDTO);
        }
        return jobWithCompanyDTOs;
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        {
            if (jobOptional.isPresent()) {
                Job job = jobOptional.get();
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                jobRepository.save(job);
                return true;
            }
            return false;
        }
    }

}
