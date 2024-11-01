const addForm = document.querySelector("#addForm")

const submitBtn = document.querySelector(".btn");
const todoTitle = document.querySelector("#todoTitle");
const todoContent = document.querySelector("#todoContent");


addForm.addEventListener("submit", e=>{

 const inputTitle = todoTitle.value.trim();
 const inputContent = todoContent.value.trim();

 if(inputTitle.length === 0){
  e.preventDefault();

  alert("제목을 입력해 주세요");
  todoTitle.focus();
 }

 if(inputContent.length === 0){
  e.preventDefault();

  alert("내용을 입력해 주세요");
  todoContent.focus();
 }

})

