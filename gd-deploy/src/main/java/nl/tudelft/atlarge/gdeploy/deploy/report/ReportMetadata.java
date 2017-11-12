package nl.tudelft.atlarge.gdeploy.deploy.report;

import lombok.Data;

import java.util.List;

@Data
public class ReportMetadata {

    private long projectId;


    private List<String> dataSets;

    private List<String> algorithms;

    private boolean success;

}
