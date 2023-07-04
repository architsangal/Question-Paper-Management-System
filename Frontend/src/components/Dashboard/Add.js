import React, { useState } from 'react';
import Swal from 'sweetalert2';
import axios from 'axios';
const Add = ({ questions, setQuestions, setIsAdding }) => {
  const [quesType, setQuesType] = useState('MCQ');
  const [questionText, setQuestionText] = useState('');
  const [questionDomain, setQuestionDomain] = useState('');
  const [options, setOptions] = useState(Array(4).fill(''));
  const [correctOption, setCorrectOption] = useState(1);
  const [quesAns, setQuesAns] = useState('');
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
      domain: questionDomain,
      authorId : JSON.parse(localStorage.getItem('author_id')),
      status : 'ACTIVE',
      type : quesType == 'MCQ' ? 2 : 1,
      mcq : quesType == 'MCQ' ? options.map((x,i)=>({quesText: questionText, optionText:options[i],isCorrect:correctOption==i+1})) : null,
      subq : quesType == 'MCQ' ? null : {quesText: questionText, ans:quesAns},
    };
    console.log(newEmployee);
    // send data to http://localhost:8090/item/add using axios
    axios.post('http://localhost:8090/item/add', newEmployee)
    .then(res => {
      console.log(res);
      console.log(res.data);
      questions.push(res.data);
      // refresh page
      Swal.fire({
        icon: 'success',
        title: 'Added!',
        text: `data has been Added.`,
        showConfirmButton: false,
        timer: 1500,
      });
      setQuestions(questions);
      setIsAdding(false);
      window.location.reload();
    })
    .catch(err => {
      console.log(err);
      // alert using sweetalert
      Swal.fire({
        icon: 'error',
        title: 'Error!',
        text: 'Error in adding question.',
        showConfirmButton: true,
        timer: 1500,
      });
    });
      // // localStorage.setItem('questions_data', JSON.stringify(questions));

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
    setCorrectOption(parseInt(e.target.value));
  };
  const handleQuesAnsChange = e => {
    setQuesAns(e.target.value);
  };

  return (
    <div className="small-container">
      <form onSubmit={handleAdd}>
      <label for="choose">Question Type</label>
      {/* write on change function for select */}
      <select id="choose" name="choose" onChange={handleQuesTypeChange}>
        <option selected >MCQ</option>
        <option >Subjective</option>
      </select>
      <label for="question">Question Text</label>
      <input type="text" id="question" name="question" onChange={handleQuestionTextChange} />
      <label for="domain">Domain</label>
      <input type="text" id="domain" name="domain" onChange={handleQuestionDomainChange} />
      {quesType === 'MCQ' ? (
        // Take input for mcq question
        <div>
          <label for="numOptions">Number of Options</label>
          <input type="number" id="numOptions" name="numOptions" defaultValue={4} onChange={handleNumOptionsChange} />
          <label for="options">Options</label>
          {options.map((option,i) => (
          <div>
            <label for={"option"+ (i+1)}>Option {i+1}</label>
            <input type="text" id={(i+1)} name={"option"+ (i+1)} onChange={handleOptionsChange} />
            {console.log("options",options)}
          </div>    
          ))}
          <label for="correctOption">Correct Option</label>
          <select id="correctOption" name="correctOption" onChange={handleCorrectOptionChange}>
            {options.map((option, i) =>(
              <option key = {i+1} value={i+1} >Option {i+1}</option>
            ))}
          </select>
          </div>
          ) : (
          <div>
          <label for="answer">Answer</label>
          <input type="text" id="answer" name="answer" onChange={handleQuesAnsChange} />
        </div>
      )}
              <div style={{ marginTop: '30px' }}>
          <input type="submit" value="Add" />
          <input
            style={{ marginLeft: '12px' }}
            className="muted-button"
            type="button"
            value="Cancel"
            onClick={() => setIsAdding(false)}
          />
        </div>  
      </form>
    </div>
  );
};

export default Add;
