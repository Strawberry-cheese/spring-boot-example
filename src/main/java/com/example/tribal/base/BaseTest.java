package com.example.tribal.base;

import com.example.tribal.SpringBootExampleApplication;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootExceptionReporter;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: ；lh
 * @Description: TODO
 * @DateTime: 2022/7/27$ 下午4:05$
 * @Params: $
 * @Return $
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootExampleApplication.class)
public class BaseTest
{
    @Autowired
    protected RepositoryService repositoryService;
    @Autowired
    protected RuntimeService runtimeService;
    @Autowired
    protected TaskService taskService;
    @Autowired
    protected HistoryService historyService;
    @Autowired
    protected ManagementService managementService;
    /**
     * 这个类注入不进来，报错
     */
//    @Autowired
//    protected DynamicBpmnService dynamicBpmnService;
    @Autowired
    protected ProcessEngineConfiguration processEngineConfiguration;


    //流程部署
    @Test
    public void deploymentDefinition(){
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/MyLeave.bpmn")
                .name("请假流程")
                .deploy();
        System.out.printf("部署流程成功：Id:{},Key:{},Name:{}",deployment.getId(),deployment.getKey(),deployment.getName());
    }
}