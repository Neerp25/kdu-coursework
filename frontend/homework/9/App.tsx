
import './App.css';

import Main from './Main';


function App() {
 
  let input = {
    "name": "Amey",
    "fullName": "Amey Aditya",
    "qualification": "SSE",
    "skills": [
        {
            "id": 1,
            "skill": "Python"
        },
        {
            "id": 2,
            "skill": "React"
        }
    ],
    "hobbies": [
        {
            "id": 1,
            "hobby": "Cricket"
        }
    ]
}
  return (
    
    <>

     <Main jsoninput={input}/>

    </>
    
  );
}

export default App;
