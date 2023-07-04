package com.itembank.item.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itembank.item.Entity.IdVersionSequence;
import com.itembank.item.Entity.MultipleChoiceQ;
import com.itembank.item.Entity.QuestionBank;
import com.itembank.item.Entity.QuestionVersion;
import com.itembank.item.Entity.SubjectiveQ;
import com.itembank.item.Model.Item;
import com.itembank.item.Model.MCQCompositeKey;
import com.itembank.item.Model.QuestionIdVersion;
import com.itembank.item.repositories.IdVersionSeqRepo;
import com.itembank.item.repositories.MultipleChoiceQRepo;
import com.itembank.item.repositories.QuestionBankRepo;
import com.itembank.item.repositories.QuestionVersionRepo;
import com.itembank.item.repositories.SubjectiveQRepo;

@RestController
@RequestMapping("/item")
public class ItemController {

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

	@RequestMapping(path = "/latest/author/{authorid}")
	public ResponseEntity<List<Item>> getLatestByAuthor(@PathVariable Integer authorid) {
		List<QuestionBank> quesList = quesBankRepo.findByAuthorId(authorid);

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

		return new ResponseEntity<>(items, HttpStatus.OK);
	}

	@RequestMapping(path = "/author/{authorid}")
	public ResponseEntity<List<Item>> getByAuthor(@PathVariable Integer authorid) {
		List<QuestionBank> quesList = quesBankRepo.findByAuthorId(authorid);

		List<Item> items = new ArrayList<Item>();

		for (QuestionBank ques : quesList) {
			int qid = ques.getId();
			List<QuestionVersion> quesVerList = quesVerRepo.findByQuesIdVersion_QuestionId(qid);

			for (QuestionVersion quesVer : quesVerList) {

				int ver = quesVer.getQuesIdVersion().getVersion();

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
		}

		return new ResponseEntity<>(items, HttpStatus.OK);
	}

	@RequestMapping(path = "/id/{id}")
	public ResponseEntity<List<Item>> getById(@PathVariable Integer id) {
		Optional<QuestionBank> quesOpt = quesBankRepo.findById(id);

		if (quesOpt.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		if (!quesOpt.get().getStatus().matches("ACTIVE")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		List<Item> items = new ArrayList<Item>();

		QuestionBank ques = quesOpt.get();
		int qid = ques.getId();
		List<QuestionVersion> quesVerList = quesVerRepo.findByQuesIdVersion_QuestionId(qid);

		for (QuestionVersion quesVer : quesVerList) {

			int ver = quesVer.getQuesIdVersion().getVersion();

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

		return new ResponseEntity<>(items, HttpStatus.OK);
	}

	@RequestMapping(path = "/latest/id/{id}")
	public ResponseEntity<Item> getLatestById(@PathVariable Integer id) {
		Optional<QuestionBank> quesOpt = quesBankRepo.findById(id);

		if (quesOpt.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (!quesOpt.get().getStatus().matches("ACTIVE")) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		QuestionBank ques = quesOpt.get();
		
		Optional<IdVersionSequence> idVer = idVerSeqRepo.findById(id);

		if (idVer.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		int ver = idVer.get().getVersion();
		Optional<QuestionVersion> quesVerOpt = quesVerRepo.findById(new QuestionIdVersion(id, ver));

		if (quesVerOpt.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
			List<MultipleChoiceQ> mcqList = mcqRepo.findByMcqKey_QuestionIdAndMcqKey_Version(id, ver);
			it.setMcq(mcqList);
		}

		it.setId(ques.getId());
		it.setAuthorId(ques.getAuthorId());
		it.setDomain(ques.getDomain());
		it.setStatus(ques.getStatus());
		it.setVersion(quesVer.getQuesIdVersion().getVersion());
		it.setType(quesVer.getType());
		
		return new ResponseEntity<>(it, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/id/{id}/ver/{ver}")
	public ResponseEntity<Item> getByIdAndVersion(@PathVariable Integer id, @PathVariable Integer ver) {
		Optional<QuestionBank> ques = quesBankRepo.findById(id);
		QuestionIdVersion qIdVer = new QuestionIdVersion(id, ver);
		Optional<QuestionVersion> quesVer = quesVerRepo.findById(qIdVer);

		if (ques.isEmpty() || quesVer.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		if (!ques.get().getStatus().matches("ACTIVE")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		Item it = new Item();

		if (quesVer.get().getType() == 1) {
			Optional<SubjectiveQ> subq = subqRepo.findById(qIdVer);

			if (subq.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			it.setSubq(subq.get());
		} else if (quesVer.get().getType() == 2) {

			List<MultipleChoiceQ> mcqs = mcqRepo.findByMcqKey_QuestionIdAndMcqKey_Version(id, ver);

			if (mcqs.size() == 0) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			it.setMcq(mcqs);
		}

		it.setId(ques.get().getId());
		it.setAuthorId(ques.get().getAuthorId());
		it.setDomain(ques.get().getDomain());
		it.setStatus(ques.get().getStatus());
		it.setVersion(quesVer.get().getQuesIdVersion().getVersion());
		it.setType(quesVer.get().getType());

		return new ResponseEntity<>(it, HttpStatus.OK);
	}

	@PostMapping(path = "/add")
	public ResponseEntity<Item> addItem(@RequestBody Item newItem) {

		Item it = new Item();

		QuestionBank quesToSave = new QuestionBank(newItem.getDomain(), newItem.getAuthorId(), newItem.getStatus());
		QuestionBank ques = quesBankRepo.save(quesToSave);

		IdVersionSequence idVerToSave = new IdVersionSequence(ques.getId(), 1);
		IdVersionSequence idVer = idVerSeqRepo.save(idVerToSave);

		QuestionVersion quesVerToAdd = new QuestionVersion(new QuestionIdVersion(ques.getId(), idVer.getVersion()),
				newItem.getType());
		QuestionVersion quesVer = quesVerRepo.save(quesVerToAdd);

		// Subjective
		if (quesVer.getType() == 1) {
			SubjectiveQ subqToSave = new SubjectiveQ(quesVer.getQuesIdVersion(), newItem.getSubq().getQuesText(),
					newItem.getSubq().getAns());
			SubjectiveQ subq = subqRepo.save(subqToSave);
			it.setSubq(subq);
		}
		// MCQ
		else if (quesVer.getType() == 2) {

			List<MultipleChoiceQ> mcqList = new ArrayList<MultipleChoiceQ>();

			int index = 0;
			for (MultipleChoiceQ mcqObj : newItem.getMcq()) {
				MultipleChoiceQ mcqToSave = new MultipleChoiceQ(
						new MCQCompositeKey(quesVer.getQuesIdVersion().getQuestionId(),
								quesVer.getQuesIdVersion().getVersion(), index),
						mcqObj.getQuesText(), mcqObj.getIsCorrect(), mcqObj.getOptionText());
				MultipleChoiceQ mcq = mcqRepo.save(mcqToSave);
				mcqList.add(mcq);

				index++;
			}

			it.setMcq(mcqList);
		}

		it.setId(ques.getId());
		it.setAuthorId(ques.getAuthorId());
		it.setDomain(ques.getDomain());
		it.setStatus(ques.getStatus());
		it.setVersion(quesVer.getQuesIdVersion().getVersion());
		it.setType(quesVer.getType());

		return new ResponseEntity<>(it, HttpStatus.OK);
	}

	@PostMapping(path = "/update")
	public ResponseEntity<Item> updateItem(@RequestBody Item newItem) {

		Optional<QuestionBank> ques = quesBankRepo.findById(newItem.getId());
		Optional<QuestionVersion> quesVer = quesVerRepo
				.findById(new QuestionIdVersion(newItem.getId(), newItem.getVersion()));
		Optional<IdVersionSequence> idVer = idVerSeqRepo.findById(newItem.getId());

		if (ques.isEmpty() || quesVer.isEmpty() || idVer.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		if (!ques.get().getStatus().matches("ACTIVE")) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		Item it = new Item();

		IdVersionSequence idVerToUpdate = idVer.get();
		int newVer = idVerToUpdate.getVersion() + 1;
		idVerToUpdate.setVersion(newVer);
		IdVersionSequence idv = idVerSeqRepo.save(idVerToUpdate);

		QuestionBank quesToUpdate = ques.get();
		if(newItem.getAuthorId() != 0) {			
			quesToUpdate.setAuthorId(newItem.getAuthorId());
		}
		if(newItem.getDomain() != null) {
			quesToUpdate.setDomain(newItem.getDomain());
		}
		if(newItem.getStatus() != null) {			
			quesToUpdate.setStatus(newItem.getStatus());
		}
		QuestionBank q = quesBankRepo.save(quesToUpdate);

		QuestionVersion quesVerToSave = new QuestionVersion();
		quesVerToSave.setQuesIdVersion(new QuestionIdVersion(q.getId(), idv.getVersion()));
		if(newItem.getType() != 0) {			
			quesVerToSave.setType(newItem.getType());
		}
		QuestionVersion qv = quesVerRepo.save(quesVerToSave);

		// Subjective
		if (qv.getType() == 1) {
			if(newItem.getSubq() != null) {
				SubjectiveQ subqToSave = new SubjectiveQ(qv.getQuesIdVersion(), newItem.getSubq().getQuesText(),
						newItem.getSubq().getAns());
				SubjectiveQ subq = subqRepo.save(subqToSave);
				it.setSubq(subq);				
			}
		}
		// MCQ
		else if (qv.getType() == 2) {
			if(newItem.getMcq() != null) {
				List<MultipleChoiceQ> mcqList = new ArrayList<MultipleChoiceQ>();

				int index = 0;
				for (MultipleChoiceQ mcqObj : newItem.getMcq()) {
					MultipleChoiceQ mcqToSave = new MultipleChoiceQ(
							new MCQCompositeKey(qv.getQuesIdVersion().getQuestionId(), qv.getQuesIdVersion().getVersion(),
									index),
							mcqObj.getQuesText(), mcqObj.getIsCorrect(), mcqObj.getOptionText());
					MultipleChoiceQ mcq = mcqRepo.save(mcqToSave);
					mcqList.add(mcq);

					index++;
				}

				it.setMcq(mcqList);				
			}
		}

		it.setId(q.getId());
		it.setAuthorId(q.getAuthorId());
		it.setDomain(q.getDomain());
		it.setStatus(q.getStatus());
		it.setVersion(qv.getQuesIdVersion().getVersion());
		it.setType(qv.getType());

		return new ResponseEntity<>(it, HttpStatus.OK);
	}

	@PostMapping(path = "/delete/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable Integer id) {
		Optional<QuestionBank> ques = quesBankRepo.findById(id);

		if (ques.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		QuestionBank quesToDelete = ques.get();
		quesToDelete.setStatus("DELETED");
		quesBankRepo.save(quesToDelete);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
