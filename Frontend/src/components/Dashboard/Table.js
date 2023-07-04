import React from 'react';

const Table = ({ questions, handleEdit, handleDelete, handleView, isPaper=false }) => {
  // questions.forEach((question, i) => {
  //   question.id = i + 1;
  // });

  const formatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
    minimumFractionDigits: null,
  });

  return (
    <div className="contain-table">
      <table className="striped-table">
        <thead>
          <tr>
            <th>S.No.</th>
            <th>Qid</th>
            <th>Domain</th>
            <th>Type</th>
            <th>Version</th>
            <th>Status</th>
            <th colSpan={isPaper?1: 3} className="text-center">
              Actions
            </th>
          </tr>
        </thead>
        <tbody>
          {questions.length > 0 ? (
            questions.map((question, i) => (
              <tr key={i+1}>
                <td>{i + 1}</td>
                <td>{question.id}</td>
                <td>{question.domain}</td>
                <td>{question.type == 2 ? 'MCQ': 'Subjective'}</td>
                <td>{question.version} </td>
                <td>{question.status}</td>

                <td className="text-right">
                  <button
                    onClick={() => handleView(question.id, question.version)}
                    className="button"
                    style={{ backgroundColor: '#4CAF50', border: 'none' }}
                  >
                    View
                  </button>
                </td>
                {!isPaper && 
                <td className="text-center">
                  <button
                    onClick={() => handleEdit(question.id)}
                    className="button"
                  >
                    Edit
                  </button>
                </td>
                }
                {!isPaper &&
                <td className="text-left">
                  <button
                    onClick={() => handleDelete(question.id)}
                    className="button"
                    style={{ backgroundColor: '#ff0000', borderColor: 'none' }}
                  >
                    Delete
                  </button>
                </td>
                }
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan={7}>No Questions</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default Table;
