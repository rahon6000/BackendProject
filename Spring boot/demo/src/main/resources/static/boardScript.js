function openWritePanel() {
  document.getElementById("writePanel").style.display="block";
}

function closeWritePanel(){
  document.getElementById("writePanel").style.display="none";
}

function checkWritePanel(){
  if (isEmpty(document.getElementsByName("name")[0].value)) {
    alert("이름을 입력해 주세요");
    return false;
  } else if (isEmpty(document.getElementsByName("title")[0].value)) {
    alert("제목을 입력해 주세요");
    return false;
  } else if (isEmpty(document.getElementsByName("content")[0].value)) {
    alert("내용을 입력해 주세요");
    return false;
  }
  return true;
}

function checkReply(){
  if(isEmpty(document.getElementsByName("replyName")[0].value)){
    alert("이름을 입력해 주세요")
    return false;
  } else if (isEmpty(document.getElementsByName("replyContent")[0].value)){
    alert("내용을 입력해 주세요")
    return false;
  }
  return true;
}

function isEmpty(str){
  return (str === "");
}