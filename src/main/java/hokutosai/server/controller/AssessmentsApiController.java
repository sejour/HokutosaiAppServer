package hokutosai.server.controller;

import hokutosai.server.data.entity.assessments.AssessmentReportCause;
import hokutosai.server.data.repository.assessments.AssessmentReportCauseRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/assessments")
public class AssessmentsApiController {

	@Autowired
	private AssessmentReportCauseRepository assessmentReportCauseRepository;

	@RequestMapping(value = "/reports/causes", method = RequestMethod.GET)
	public List<AssessmentReportCause> getReportsCauses() {
		return this.assessmentReportCauseRepository.findAll(new Sort(Sort.Direction.DESC, "sequence"));
	}
}
