package com.babar.jobmicroservice.job.dto;

import com.babar.jobmicroservice.job.Job;
import com.babar.jobmicroservice.job.external.Company;

public class JobWithCompanyDTO {
    private Job job;
    private Company company;
    public Job getJob() {
        return job;
    }
    public void setJob(Job job) {
        this.job = job;
    }
    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }

}
