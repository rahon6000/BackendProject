let panelOn = false;

function openWritePanel() {
  if (panelOn) { return }
  document.getElementById("writePanel").style.display="block";
  panelOn = true;
}

function closeWritePanel(){
  document.getElementById("writePanel").style.display="none";
  panelOn = false;
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

function openModPanel() {
  if (panelOn) { return }
  document.getElementById("modPanel").style.display="block";
  document.getElementById("modName").value = 
    document.getElementById("contentPanelAuthor").innerText.split(" : ")[1];
  document.getElementById("modTitle").value = 
    document.getElementById("contentPanelTitle").innerText.split(" : ")[1];
  document.getElementById("modContent").value = 
    document.getElementById("contetnPanelContent").innerText;

  panelOn = true;
}

function closeModPanel(){
  document.getElementById("modPanel").style.display="none";
  panelOn = false;
}

function checkModPanel(){
  if (isEmpty(document.getElementsByName("modName")[0].value)) {
    alert("이름을 입력해 주세요");
    return false;
  } else if (isEmpty(document.getElementsByName("modTitle")[0].value)) {
    alert("제목을 입력해 주세요");
    return false;
  } else if (isEmpty(document.getElementsByName("modContent")[0].value)) {
    alert("내용을 입력해 주세요");
    return false;
  }
  // 패스워드 체크
  return true;
}

function askPwdForDelete(){
  let password = prompt("enter the password");
  document.getElementsByName("password")[0].value = password;
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