import React from 'react'
import { Link } from 'react-router-dom';
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
function Topsection() {
  return (
    <div>
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
    </div>


  )
}

export default Topsection;
