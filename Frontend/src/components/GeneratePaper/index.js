import React, { useState, useEffect, version } from 'react';
import Swal from 'sweetalert2';
import axios from "axios";
import Table from '../Dashboard/Table'
import View from '../Dashboard/View';
// import { questionsData } from '../../data';

const GeneratePaper = ({ setIsGeneratingPaper }) => {
  const [domain, setDomain] = useState('GK');
  const [limit, setLimit] = useState(2);
  const [isTakingInput, setIsTakingInput] = useState(true);
  const [data, setData] = useState([]);
  const [isViewing, setIsViewing] = useState(false);
  const [selectedEmployee, setSelectedEmployee] = useState(null);


  const handleGeneratePaper = () => {
    setIsTakingInput(false);
    setIsGeneratingPaper(true);
    const config = {
        domain : domain,
        limit : limit
    }
    console.log("useEffect");
    axios.post("http://localhost:8090/paper/generate/", config).then((response) => {
      setData(response.data);
    });
}
    const handleView = (id,version) => {
    const [question] = data.filter(question => question.id === id && question.version == version);
    setSelectedEmployee(question);
    setIsViewing(true);
  };

  return (
    <div className="container">
      {/* take input of string domain and int limit */}
      {!isViewing && (
        <div className="row">
          <div className="col-md-12">
            <div className="card">
              <div className="card-header">
                <h4 className="card-title">Enter the domain and limit</h4>
              </div>
              <div className="card-body">
                <div className="row">
                  <div className="col-md-6 pr-1">
                    <div className="form-group">
                      <label>Domain</label>
                      <input
                        type="text"
                        className="form-control"
                        placeholder="Domain"
                        onChange={(e) => setDomain(e.target.value)}
                      />
                    </div>
                  </div>
                  <div className="col-md-6 pl-1">
                    <div className="form-group">
                      <label>Limit</label>
                      <input
                        type="number"
                        className="form-control"
                        placeholder="Limit"
                        onChange={(e) => setLimit(e.target.value)}
                      />
                    </div>
                  </div>
                </div>
                <input
                  className="muted-button"
                  type="button"
                  value="Go Back"
                  onClick={() => setIsGeneratingPaper(false)}
                />
                <input
                  className="button"
                  type="button"
                  value="Generate Paper"
                  style={{ marginLeft: "10px" }}
                  onClick={handleGeneratePaper}
                />
              </div>
            </div>
          </div>
        </div>
      )}
      {!isTakingInput && !isViewing && (
        <Table questions={data} handleView={handleView} isPaper={true} />
      )}
      {isViewing && (
        <View data={selectedEmployee} setIsViewing={setIsViewing} />
      )}
    </div>
  );
};

export default GeneratePaper;
