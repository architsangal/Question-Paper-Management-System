import React, { useState } from 'react';
import Swal from 'sweetalert2';
import axios from 'axios';

const Edit = ({ questions, data, setQuestions, setIsEditing }) => {
  const [quesType, setQuesType] = useState(data.type == 2 ? 'MCQ' : 'Subjective');
  const [questionText, setQuestionText] = useState(data.type == 2 ? data.mcq[0].quesText : data.subq.quesText);
  const [questionDomain, setQuestionDomain] = useState(data.domain);
  const [options, setOptions] = useState(data.type == 2 ? data.mcq.map(x=>x.optionText) : Array(4).fill(''));
  let valIdx = 1;
  if(data.type == 2){
  for (let index = 0; index < data.mcq.length; index++) {
    const element = data.mcq[index];
    if(element.isCorrect){
      valIdx = index+1;
      console.log("valIdx",valIdx);
      break;
    }
  }
  }
  const [correctOption, setCorrectOption] = useState(data.type == 2 ? valIdx : 1);
  const [quesAns, setQuesAns] = useState(data.type == 2 ? '' : data.subq.ans);
  const handleAdd = e => {
    e.preventDefault();

    if (!quesType || !questionText || !((options&&correctOption) || quesAns) ) {
      return Swal.fire({
        icon: 'error',
        title: 'Error!',
        text: 'All fields are required.',
        showConfirmButton: true,
      });
    }
    const newEmployee = {
      id: data.id,
      domain: questionDomain,
      authorId : data.authorId,
      version: data.version,
      status : 'ACTIVE',
      type : quesType == 'MCQ' ? 2 : 1,
      mcq : quesType == 'MCQ' ? options.map((x,i)=>({quesText: questionText, optionText:options[i],isCorrect:correctOption==i+1})) : null,
      subq : quesType == 'MCQ' ? null : {quesText: questionText, ans:quesAns},
    };
    // send data to http://localhost:8090/item/add using axios
    axios.post('http://localhost:8090/item/update', newEmployee)
    .then(res => {
      console.log(res);
      console.log(res.data);
      // refresh page
      Swal.fire({
        icon: 'success',
        title: 'Updated!',
        text: `data has been Updated.`,
        showConfirmButton: false,
        timer: 1500,
      });
      questions.push(res.data);
      // localStorage.setItem('questions_data', JSON.stringify(questions));
      setQuestions(questions);
      setIsEditing(false);
      window.location.reload();
    })
    .catch(err => {
      console.log(err);
      // alert using sweetalert
      Swal.fire({
        icon: 'error',
        title: 'Error!',
        text: 'Error in updating question.',
        showConfirmButton: true,
      });
    });

  };
  const handleQuesTypeChange = e => {
    setQuesType(e.target.value);
  };
  const handleQuestionTextChange = e => {
    setQuestionText(e.target.value);
  };
  const handleQuestionDomainChange = e => {
    setQuestionDomain(e.target.value);
  };
  const handleNumOptionsChange = e => {
    const newOptions = new Array(parseInt(e.target.value)).fill('');
    console.log("newOptions",newOptions);
    setOptions(newOptions);
  };
  const handleOptionsChange = e => {
    let newOptions = [...options];
    console.log(e.target.id);
    newOptions[e.target.id - 1] = e.target.value;
    setOptions(newOptions);
  };
  const handleCorrectOptionChange = e => {
    setCorrectOption(e.target.value);
  };
  const handleQuesAnsChange = e => {
    setQuesAns(e.target.value);
  };

  return (
    <div className="small-container">
      <form onSubmit={handleAdd}>
      <label for="choose">Question Type</label>
      {/* write on change function for select */}
      <select id="choose" name="choose" defaultValue={quesType} onChange={handleQuesTypeChange}>
        <option selected >MCQ</option>
        <option >Subjective</option>
      </select>
      <label for="question">Question Text</label>
      <input type="text" id="question" name="question" defaultValue={questionText} onChange={handleQuestionTextChange} />
      <label for="domain">Domain</label>
      <input type="text" id="domain" name="domain" defaultValue={questionDomain} onChange={handleQuestionDomainChange} />
      {quesType === 'MCQ' ? (
        // Take input for mcq question
        <div>
          <label for="numOptions">Number of Options</label>
          <input type="number" id="numOptions" name="numOptions" defaultValue={options.length} onChange={handleNumOptionsChange} />
          <label for="options">Options</label>
          {options.map((option,i) => (
          <div>
            <label for={"option"+ (i+1)}>Option {i+1}</label>
            <input type="text" id={(i+1)} name={"option"+ (i+1)} defaultValue={options[i]} onChange={handleOptionsChange} />
            {console.log("options",options)}
          </div>    
          ))}
          <label for="correctOption">Correct Option</label>
          <select id="correctOption" name="correctOption" defaultValue={correctOption} onChange={handleCorrectOptionChange}>
            {options.map((option, i) =>(
              <option key = {i+1} value={(i+1)}>Option {i+1}</option>
            ))}
          </select>
          </div>
          ) : (
          <div>
          <label for="answer">Answer</label>
          <input type="text" id="answer" name="answer" defaultValue={quesAns} onChange={handleQuesAnsChange} />
        </div>
      )}
              <div style={{ marginTop: '30px' }}>
          <input type="submit" value="Edit" />
          <input
            style={{ marginLeft: '12px' }}
            className="muted-button"
            type="button"
            value="Cancel"
            onClick={() => setIsEditing(false)}
          />
        </div>  
      </form>
    </div>
  );
};

export default Edit;
