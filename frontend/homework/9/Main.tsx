import React from 'react'

import Skills, { Skill } from './Skills'
import Hobbies, { Hobby } from './Hobbies'
import "./Main.css"
interface Mainprops{
    jsoninput:{
        name:string,
        fullName:string,
        qualification:string,
        skills : Array<Skill>,
        hobbies: Array<Hobby>
    }
}
function Main(props: Mainprops) {
  return (
    <div>
        <div id="top-section">
        <h3>{props.jsoninput.name}</h3>
        <h3>{props.jsoninput.fullName}</h3>
        <h3>{props.jsoninput.qualification}</h3>
        </div>
        
        <div id ="mid-section">
        <Skills skills={props.jsoninput.skills}/>
        <Hobbies hobbies={props.jsoninput.hobbies}/> 
        </div>
    </div>
  )
}

export default Main
