import React from 'react'

export interface Hobby {
    id: number;
    hobby: string;
}
interface Hobbiesprop{
    hobbies:Array<Hobby>
}
function Hobbies(props:Hobbiesprop) {
  return (
    <div id="hobbiesbox">
        <div id="heading">
        <h3>Hobbies</h3>
        </div>

       {props.hobbies.map((hobby) => (
                <h3 id="hobbytag" key={hobby.id}>{hobby.hobby}</h3>
            ))}      
    </div>
  )
}

export default Hobbies
