const express = require('express');
// const path = require('path');
const uuid = require(`uuid`);
const app = express();
const members = require('./Member');


//Body passar Middleware
app.use(express.json());
app.use(express.urlencoded({extended:false}));


// this route to Add member
app.post('/',(req,res)=>{
    const newMember={
                id : uuid.v4(),
                name:req.body.name,
                email:req.body.email
        
             }
             if(!newMember.name || !newMember.email){
                return res.status(400).json({msg:'Please include a name and email'});
             }
             members.push(newMember);
        
             return res.json(members);
})



// this route get all members
app.get('/',(req,res)=>{
    return res.json(members);
});

// Get Single Member by id
app.get('/api/members/:id',(req,res)=>{
    const found = members.some(member => member.id === req.params.id);

    if (found) {
        res.json(members.filter(member => member.id === req.params.id));
    } else {
        res.status(400).json({ msg: `No Member with the id of ${req.params.id}` });
    }
});



// update member
app.put('/:id',(req,res)=>{
    const found = members.some(member=>member.id === req.params.id);
    if (found){
        const upMem= req.body;
        members.forEach(member=>{
            if (member.id=== req.params.id){
                member.name=upMem.name ? upMem.name:member.name;
                member.email=upMem.email?upMem.email:member.email;

                res.json({msg:'Member updated',member});
            }
        });
    }else{
        res.status(400).json({msg :`NO memeber id ${req.params.id}`});
    }
});



const PORT = process.nextTick.PORT || 5000;


app.listen(PORT,()=>console.log(`running............`))
