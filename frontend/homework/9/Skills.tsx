
export interface Skill {
    id: number;
    skill: string;
}
interface Skillsprop{
    skills:Array<Skill>
}
function Skills(props:Skillsprop) {
  return (
    <div id="Skillbox">
        <div id="heading">
          <h3>Skills</h3> 
        </div>
        
        {props.skills.map((skill) => (
                <h3 id="skilltag"key={skill.id}>{skill.skill}</h3>
            ))} 
        
    </div>
  )
}

export default Skills
