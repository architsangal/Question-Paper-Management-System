package com.itembank.papergen.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itembank.papergen.Entity.IdVersionSequence;
import com.itembank.papergen.Model.QuestionIdVersion;
import com.itembank.papergen.Entity.MultipleChoiceQ;
import com.itembank.papergen.Entity.QuestionBank;
import com.itembank.papergen.Entity.QuestionVersion;
import com.itembank.papergen.Entity.SubjectiveQ;
import com.itembank.papergen.Model.Item;
import com.itembank.papergen.Model.PaperConfig;
import com.itembank.papergen.repositories.IdVersionSeqRepo;
import com.itembank.papergen.repositories.MultipleChoiceQRepo;
import com.itembank.papergen.repositories.QuestionBankRepo;
import com.itembank.papergen.repositories.QuestionVersionRepo;
import com.itembank.papergen.repositories.SubjectiveQRepo;

@RestController
@RequestMapping("/paper")
public class PaperGenController {

	@Autowired
	private QuestionBankRepo quesBankRepo;
	@Autowired
	private QuestionVersionRepo quesVerRepo;
	@Autowired
	private IdVersionSeqRepo idVerSeqRepo;
	@Autowired
	private SubjectiveQRepo subqRepo;
	@Autowired
	private MultipleChoiceQRepo mcqRepo;

	@PostMapping(path = "/generate")
	public ResponseEntity<List<Item>> getRandomPaper(@RequestBody PaperConfig newPaperConfig) {
		String domain = newPaperConfig.getDomain();
		int limit = newPaperConfig.getLimit();

		List<QuestionBank> quesList = quesBankRepo.findByDomain(domain);
		
		List<Item> items = new ArrayList<Item>();

		for (QuestionBank ques : quesList) {
			int qid = ques.getId();
			Optional<IdVersionSequence> idVer = idVerSeqRepo.findById(qid);

			if (idVer.isEmpty()) {
				continue;
			}
			int ver = idVer.get().getVersion();

			Optional<QuestionVersion> quesVerOpt = quesVerRepo.findById(new QuestionIdVersion(qid, ver));

			if (quesVerOpt.isEmpty()) {
				continue;
			}

			QuestionVersion quesVer = quesVerOpt.get();

			Item it = new Item();

			// Subjective
			if (quesVer.getType() == 1) {
				Optional<SubjectiveQ> subq = subqRepo.findById(quesVer.getQuesIdVersion());

				if (subq.isPresent()) {
					it.setSubq(subq.get());
				}
			}
			// MCQ
			else if (quesVer.getType() == 2) {
				List<MultipleChoiceQ> mcqList = mcqRepo.findByMcqKey_QuestionIdAndMcqKey_Version(qid, ver);
				it.setMcq(mcqList);
			}

			it.setId(ques.getId());
			it.setAuthorId(ques.getAuthorId());
			it.setDomain(ques.getDomain());
			it.setStatus(ques.getStatus());
			it.setVersion(quesVer.getQuesIdVersion().getVersion());
			it.setType(quesVer.getType());

			items.add(it);
		}

        Collections.shuffle(items);
        
        int size = items.size();
		List<Item> output = new ArrayList<Item>();
        
        for(int i = 0; i < Math.min(limit, size); i++) {
        	output.add(items.get(i));
        }

		return new ResponseEntity<>(output, HttpStatus.OK);
	}
	
}
