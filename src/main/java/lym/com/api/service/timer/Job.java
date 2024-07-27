package lym.com.api.service.timer;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;

import lym.com.api.repository.base.AbonnementRepo;

@Component
@DisallowConcurrentExecution
@ConditionalOnProperty(name = "quartz.enabled")
public class Job implements org.quartz.Job {

	@Autowired
	AbonnementRepo abonnementService;

	public Job() {
		System.err.println("Job Instantiated "+frequency);
	}

	@Value("${cron.frequency.jobwithsimpletrigger}")
	private long frequency;

	@Bean(name = "jobWithSimpleTriggerBean")
	public JobDetailFactoryBean sampleJob() {
		return ScheduleConfig.createJobDetail(this.getClass());
	}

	@Bean(name = "jobWithSimpleTriggerBeanTrigger")
	public SimpleTriggerFactoryBean sampleJobTrigger(@Qualifier("jobWithSimpleTriggerBean") JobDetail jobDetail) {
		return ScheduleConfig.createTrigger(jobDetail, frequency);
	}
	
	@Override
	public void execute(JobExecutionContext jobExecutionContext) {
		Long count=abonnementService.count();
		System.out.println("Nombre d'abonnement :"+count);
		
	}
}
