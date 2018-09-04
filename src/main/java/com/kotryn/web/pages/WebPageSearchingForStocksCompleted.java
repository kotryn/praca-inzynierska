package com.kotryn.web.pages;

import com.kotryn.entity.Job;
import com.kotryn.entity.Sector;
import com.kotryn.json.*;
import com.kotryn.repository.JobRepository;

import java.util.*;

public class WebPageSearchingForStocksCompleted {

    private JobRepository jobRepository;
    private final Long jobId;

    public WebPageSearchingForStocksCompleted(Long jobId, JobRepository jobRepository) {
        this.jobRepository = jobRepository;
        this.jobId = jobId;
    }

    public Page show() {
        Job job = jobRepository.findOne(jobId);

        List<String> previouslySelectedStocks = new ArrayList<>();
        previouslySelectedStocks.addAll(job.getSelectedStocks());

        List<Entity> header = new ArrayList<>();
        List<Entity> body = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://localhost:8080/start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        body.add(new Entity<>(new Text("text", "Searching for stocks completed successfully")));

        if(previouslySelectedStocks.size() <= 0){
            body.add(new Entity<>(new Text("text", "Previously selected stocks: none")));
        }else{
            List<Entity> list = new ArrayList<>();
            list.add(new Entity<>(new StaticList("list", previouslySelectedStocks)));

            body.add(new Entity<>(new Dropdown("dropdown", "Previously selected stocks", list)));
        }

        body.add(new Entity<>(new Title("title", "h4", "Available stocks")));

        Map<String, Sector> map = new HashMap<>(job.getStocks());

        for (Map.Entry<String, Sector> entry : map.entrySet()){
            String sector = entry.getKey();
            Set<String> industry = new HashSet<>();

            for(Map.Entry<String, String> element : entry.getValue().getIndustriesStocks().entrySet()){
                industry.add(element.getValue());
            }
            List<Entity> dropdown = new ArrayList<>();

            for(String i: industry){
                List<String> name = job.getAllKeysForValue(entry.getValue().getIndustriesStocks(), i);
                List<Entity> nameList = new ArrayList<>();
                nameList.add(new Entity<>(new Checkbox("checkbox", name, name)));
                dropdown.add(new Entity<>(new Dropdown("dropdown", i, nameList)));
            }
            body.add(new Entity<>(new Dropdown("dropdown", sector, dropdown)));
        }

        body.add(new Entity<>(new Button("button-back", "http://localhost:8080/jobsPOST/"+jobId, "Back")));
        body.add(new Entity<>(new Button("button-form", "/calculating_sample_count_setup/"+jobId, "Next")));

        return new Page(new Header(header), new Body(body));
    }
}
