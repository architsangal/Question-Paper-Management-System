import React from 'react';

import Logout from '../Logout';

const Header = ({ setIsAdding, setIsAuthenticated, setIsGeneratingPaper }) => {
  return (
    <header>
      <h1>Item Bank Management Software</h1>
      <div style={{ marginTop: '30px', marginBottom: '18px', }}>
        <button onClick={() => setIsAdding(true)}>Add Question</button>
        <button style ={{marginLeft:'10px'}} onClick={() => setIsGeneratingPaper(true)}>Generate Question Paper</button>
        <Logout setIsAuthenticated={setIsAuthenticated} />
      </div>
    </header>
  );
};

export default Header;
