import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import { Provider } from 'react-redux';

import { store } from './Slices/store';
import Header from './header/Header';

import './App.css';
import Portfolio from './portfoliopage/Portfolio';


const headercontainer: React.CSSProperties = {
 fontFamily: "Poppins",
 width: "100%",
 backgroundColor: "#1871c2",
 height: "60px",
 color: "white",
 display: "flex",
 flexDirection: "row",
 justifyContent: "space-between",
 alignItems: "center"
};

const left: React.CSSProperties = {
 display: "flex",
 width: "20%",
 flexDirection: "row",
 alignItems: "center",
 justifyContent: "space-around",
 fontSize: "1em"
};

const heading: React.CSSProperties = {
 marginLeft: "10px",
 marginTop: "29px",
};
const buttonStyle: React.CSSProperties = {
  color: "white",
  textDecoration: "none",
  background: "none",
  border: "none",
  padding: 0,
  cursor: "pointer",
 };
 const headingprotfolio:React.CSSProperties={
  fontFamily: "Poppins",
 }
 
function App() {
 return (
  
    <Provider store={store}>
      <Router>
        
        <div id="main-container">
          
          <div className="headerContainer" style={headercontainer}>
            <div>
              <h1><i className="fi fi-rs-chart-histogram" style={heading}></i> KDU Stock Market</h1>
            </div>
            <div style={left}>
              <h3>Summarizer</h3>
              <Link to="/profile">
 <button style={buttonStyle}>
    <h3 style={headingprotfolio}>My Portfolio</h3>
 </button>
</Link>  
            </div>
          </div>
          <Routes>
            <Route path="/" element={<Header/>} />
            <Route path="/profile" element={<Portfolio/>} />
          </Routes>
        </div>
      </Router>
    </Provider>
 );
}

export default App;


