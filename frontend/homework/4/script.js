let add = document.getElementById("addbtn");

add.addEventListener("click", addtodo);

function addtodo() {
    let inputtag = document.getElementById("Todo-input");
    const data = inputtag.value;
    if(data===""){
        return
    }
    const todolistitem = document.createElement("li");
    todolistitem.textContent = data;

    let deletebutton = document.createElement("button");
    deletebutton.textContent = "Delete";
    deletebutton.addEventListener("click", function () {
        todolist.removeChild(todolistitem);
    });


    let todolist = document.getElementById("Todo-List");
    todolistitem.appendChild(deletebutton);

    todolist.appendChild(todolistitem);
}