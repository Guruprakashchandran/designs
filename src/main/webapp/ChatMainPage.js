var user;
var groupnames = [];
var groupIds = [];
var groupChatclickid, privatechatclickid;
var showgroupnames = true;
var personId = [];
var names = [];
var groupmessages = [];
var privatemessages = [];
var privatechatclick;
var isPrivateChatFirstEntry = true,
  isGroupChatFirstEntry = true;
var personalmsg = true;

function messagesList() {
  document.querySelector(".profileViewDiv").style = "display: none";
}

function friendMessage() {
  let word = "guru<br>guru<br>guru<br>";
  let height = 3 * 100;
  document.querySelector(".friendMessage").style = "height : ";
}
function bodymessages() {
  document.querySelector(".profileViewDiv").style = "display: none";
}
function backgroundContainer() {
  document.querySelector(".profileViewDiv").style = "display: none";
}
// var profileView = 0;
function showProfile() {
  // if (profileView % 2 == 0) {
  document.querySelector(".profileViewDiv").style = "display: block";
  // } else {
}

function personalchatclick() {
  document.querySelector(".profileViewDiv").style = "display: none";
  document.querySelector(".groupChat").style = "background-color : #3e4e53";
  document.querySelector(".privateChat").style = "background-color : white";
  document.querySelector(".usersicon").style = "color : white";
  document.querySelector(".usericon").style = "color : #3e4e53";
  if (isPrivateChatFirstEntry == true) {
    isPrivateChatFirstEntry = false;
    console.log("reached");
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
      if (xhr.readyState == 4 && this.status == 200) {
        var privateChats = JSON.parse(this.responseText);
        names = privateChats["friendsName"];
        privatemessages = privateChats["messages"];
        personId = privateChats["personId"];
        // console.log(privatemessages);
        user = privateChats.userDetails;
        setProfileDetails();

        // console.log(user.mobileno);
        if (personalmsg == true) {
          displayPersonalFriendsList(names);
          // personalmsg = false;
        } else {
          showPersonalMessages(privatechatclickid);
        }
      }
    };
    xhr.open("GET", "http://localhost:8085/ChatApp/personalchat", true);
    xhr.send();
  } else {
    displayPersonalFriendsList(names);
  }
}

function setProfileDetails() {
  console.log(user.name);
  document.querySelector(".userspan").innerHTML = user.name;
  document.querySelector(".mobilespan").innerHTML = user.mobileno;
  document.querySelector(".emailspan").innerHTML = user.emailid;
  document.querySelector(".dobspan").innerHTML = user.dob;
  document.querySelector(".aboutspan").innerHTML = user.about;
}

function displayPersonalFriendsList(names) {
  let content = "";
  for (let i = 0; i < names.length; i++) {
    content += `<div class = "contact" onclick = "showPersonalMessages(${i})"><span class = "nameContainer">${names[i]}</span></div>`;
  }
  content =
    '<div class = "groupchatList" id = "groupchatList" style = "height : 100%">' +
    content +
    "</div";
  displayPersonalChatList(content);
}

function displayPersonalChatList(content) {
  if (content.length == 0) {
    content +=
      '<div class = "no_contacts_Container"><Span class = "nocontacts">No Contacts</span></div>';
    document.getElementById("chatLists").innerHTML = content;
  } else {
    let searchBar =
      '<div class = "userSearchDiv"><input type = "text" onclick = "filterFriends()" class = "userSearch" id = "searchFriend" placeholder = "Search Friend"><i class="fa-regular fa-square-plus" id = "plusicon" onclick = "addFriend()"></i></div>';
    content = searchBar + content;
    document.getElementById("chatLists").innerHTML = content;
  }
}

function filterFriends() {
  document.querySelector(".profileViewDiv").style = "display: none";
  let searchFriend = document.getElementById("searchFriend");
  console.log(searchFriend.value);
  searchFriend.addEventListener("keyup", function (event) {
    // friendIndexs = [];
    let filterFriendsnames = names.filter((name) =>
      name.startsWith(searchFriend.value)
    );
    let FriendIndexes = [];
    let ind = 0;
    if (filterFriendsnames.length > 0) {
      for (let index = 0; index < names.length; ++index) {
        if (names[index] == filterFriendsnames[ind]) {
          FriendIndexes.push(index);
          ind++;
        }
      }
    }

    displayFilterFriendsList(filterFriendsnames, FriendIndexes);
  });
}

function displayFilterFriendsList(filterFriendsnames, FriendIndexes) {
  let content = "";
  console.log(filterFriendsnames.length);
  if (filterFriendsnames.length > 0) {
    for (let index = 0; index < filterFriendsnames.length; ++index) {
      console.log(index);
      content += `<div class = "contact" onclick = "showPersonalMessages(${FriendIndexes[index]})"><span class = "nameContainer">${filterFriendsnames[index]}</span></div>`;
    }
  } else {
    content = '<div class = "nofilterContacts">No Contacts</div>';
  }
  document.getElementById("groupchatList").innerHTML = content;
}

function showPersonalMessages(i) {
  document.querySelector(".profileViewDiv").style = "display: none";
  let messages = "";
  messages += '<div class = "messageHeader">';
  messages += `<span class = "chatNameStyle">${names[i]}</span>`;
  messages += "</div>";
  messages += '<div class = "messages" onclick = "bodymessages()">';
  messages += '<div class = "messagesAdjustment">';

  let msgArr = privatemessages[i];
  let msgObj = msgArr[0];
  let userdetail = msgObj["userdetail"];
  let frienddetail = msgObj["friend"];
  privatechatclickid = i;
  let person1msg = 0,
    person2msg = 0;
  // console.log(userdetail);
  // console.log(groupdetail);
  let displayed = true;
  while (
    userdetail != null &&
    frienddetail == null &&
    person1msg != userdetail.length
  ) {
    messages += `<div class = "yourChat"><p class = "yourMessage">${userdetail[person1msg].msg}</p></div>`;
    person1msg++;
    displayed = false;
  }
  while (
    userdetail == null &&
    frienddetail != null &&
    person2msg != frienddetail.length
  ) {
    messages += `<div class = "friendChat"><p class = "friendMessage">${frienddetail[person2msg].msg}</p></div>`;
    person2msg++;
    displayed = false;
  }
  if (displayed) {
    while (
      userdetail != null &&
      frienddetail != null &&
      (person1msg != userdetail.length || person2msg != frienddetail.length)
    ) {
      if (
        person1msg != userdetail.length &&
        person2msg != frienddetail.length
      ) {
        let compare = compareToDates(
          userdetail[person1msg].date,
          frienddetail[person2msg].date
        );
        if (compare) {
          messages += `<div class = "yourChat"><p class = "yourMessage">${userdetail[person1msg].msg}</p></div>`;
          person1msg++;
        } else {
          messages += `<div class = "friendChat"><p class = "friendMessage">${frienddetail[person2msg].msg}</p></div>`;
          person2msg++;
        }
      } else if (userdetail.length != person1msg) {
        messages += `<div class = "yourChat"><p class = "yourMessage">${userdetail[person1msg].msg}</p></div>`;
        person1msg++;
      } else if (frienddetail.length != person2msg) {
        messages += `<div class = "friendChat"><p class = "friendMessage">${frienddetail[person2msg].msg}</p></div>`;
        person2msg++;
      }
    }
  }

  messages += "</div>";
  messages += "</div>";
  messages += '<div class = "searchBar">';
  messages += `<input type = "text" class = "searchBarStyle" id = "messagebox" onclick = "getPersonalFriendMessages(${i})" placeholder = "Enter Message">`;
  messages += "</div>";
  document.querySelector(".messagesList").innerHTML = messages;
}

function getPersonalFriendMessages(i) {
  document.querySelector(".profileViewDiv").style = "display: none";
  let msg = document.getElementById("messagebox");
  msg.addEventListener("keyup", function (event) {
    if (event.keyCode === 13) {
      let text = msg.value;
      if (text.length != 0) {
        document.getElementById("messagebox").value = "";
        // console.log(text);
        addMessagesinPersonalChat(text, i);
        // text = "";
      }
    }
  });
}

function addMessagesinPersonalChat(msg, i) {
  document.querySelector(".profileViewDiv").style = "display: none";
  let xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function () {
    if (xhr.readyState == 4 && xhr.status == 200) {
      isPrivateChatFirstEntry = true;
      // showgroupnames = false;
      personalmsg = false;
      personalchatclick();
      console.log(privatemessages);
      // (privatechatclickid);
      personalchatclick(privatechatclickid);
    }
  };
  xhr.open("POST", "http://localhost:8085/ChatApp/message", true);
  xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhr.send("message=" + msg + "&personId=" + personId[i + 1]);
}

function groupchatclick() {
  document.querySelector(".groupChat").style = "background-color : white";
  document.querySelector(".privateChat").style = "background-color : #3e4e53";
  document.querySelector(".usericon").style = "color : white";
  document.querySelector(".usersicon").style = "color :#3e4e53";
  if (isGroupChatFirstEntry == true) {
    // console.log("added");
    isGroupChatFirstEntry = false;
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
      if (xhr.readyState == 4 && this.status == 200) {
        let groupChats = JSON.parse(this.responseText);
        groupnames = groupChats["groupNames"];
        groupIds = groupChats.groupIds;
        // console.log(groupIds);
        groupmessages = groupChats.messages;

        // messages(messages);
        // console.log(groupmessages);
        if (showgroupnames == true) {
          displayGroupList(groupnames, true);
        } else {
          showGroupMessages(groupChatclickid);
        }
      }
    };
    xhr.open("GET", "http://localhost:8085/ChatApp/groupchatlist", true);
    xhr.send();
  } else {
    displayGroupList(groupnames);
  }
}

function displayGroupList(groupnames) {
  let content = "";
  // if(groupnames.length > 0 || list == false){

  for (let i = 0; i < groupnames.length; i++) {
    content += `<div class = "contact" onclick = "showGroupMessages(${i})"><span class = "nameContainer">${groupnames[i]}</span></div>`;
  }
  // }
  // if(list == true){
  content =
    '<div class = "groupchatList" id = "groupchatList" style = "height : 100%">' +
    content +
    "</div>";
  displayGroupChatList(content);
}
function displayGroupChatList(content) {
  if (content.length == 0) {
    content += `<div class = "no_contacts_Container"><Span class = "nocontacts">No Contacts</span></div>`;
    document.getElementById("chatLists").innerHTML = content;
  } else if (content.length > 0) {
    let searchBar =
      '<div class = "userSearchDiv"><input type = "text" id = "searchGroup" onclick = "filterGroups()" class = "userSearch" placeholder = "Search Group"><i class="fa-regular fa-square-plus" id = "Groupplusicon" onclick = "addGroup()"></i></div>';
    content = searchBar + content;
    document.getElementById("chatLists").innerHTML = content;
  }
}

function addGroup() {}

function filterGroups() {
  document.querySelector(".profileViewDiv").style = "display: none";
  let searchGroup = document.getElementById("searchGroup");
  searchGroup.addEventListener("keyup", function (event) {
    groupIndexs = [];
    let filtergroupnames = groupnames.filter((name) =>
      name.startsWith(searchGroup.value)
    );
    let groupIndexes = [];
    let ind = 0;
    if (filtergroupnames.length > 0) {
      for (let index = 0; index < groupnames.length; ++index) {
        if (groupnames[index] == filtergroupnames[ind]) {
          groupIndexes.push(index);
          ind++;
        }
      }
    }
    displayFilterGroupList(filtergroupnames, groupIndexes);
  });
}

function displayFilterGroupList(filtergroupnames, groupIndexes) {
  let content = "";
  console.log(filtergroupnames.length);
  if (filtergroupnames.length > 0) {
    for (let index = 0; index < filtergroupnames.length; ++index) {
      console.log(index);
      content += `<div class = "contact" onclick = "showGroupMessages(${groupIndexes[index]})"><span class = "nameContainer">${filtergroupnames[index]}</span></div>`;
    }
  } else {
    content = '<div class = "nofilterContacts">No Contacts</div>';
  }
  document.getElementById("groupchatList").innerHTML = content;
}
function messages(groupmessages) {
  for (let i = 0; i < messages.length; i++) {
    let msgArr = messages[i];
    for (let j = 0; j < msgArr.length; j++) {
      let msgObj = msgArr[j];
      let userdetailArr = msgObj["userdetail"];
      // setProfileDetails();
      let groupdetail = msgObj["group"];
      // console.log(userdetailArr.length);
      for (let k = 0; k < userdetailArr.length; k++) {
        const userdetail = userdetailArr[k];
        const userId = userdetail.message;
        // console.log(1);
        // console.log(userId);
        // console.log(userdetail);
      }
    }
  }
}

function showGroupMessages(i) {
  document.querySelector(".profileViewDiv").style = "display: none";
  let messages = "";
  messages += '<div class = "messageHeader">';
  messages += `<span class = "chatNameStyle">${groupnames[i]}</span>`;
  messages += "</div>";
  messages += '<div class = "messages">';
  messages += '<div class = "messagesAdjustment">';
  let msgArr = groupmessages[i];
  let msgObj = msgArr[i];
  let userdetail = msgObj["userdetail"];
  let groupdetail = msgObj["group"];
  groupChatclickid = i;
  let person1msg = 0,
    person2msg = 0;
  console.log(userdetail);
  console.log(groupdetail);
  let displayed = true;
  while (
    userdetail != null &&
    groupdetail == null &&
    person1msg != userdetail.length
  ) {
    messages += `<div class = "yourChat"><p class = "yourMessage">${userdetail[person1msg].message}</p></div>`;
    person1msg++;
    displayed = false;
  }
  while (
    userdetail == null &&
    groupdetail != null &&
    person2msg != groupdetail.length
  ) {
    messages += `<div class = "friendChat"><p class = "friendMessage">${groupdetail[person2msg].message}</p></div>`;
    person2msg++;
    displayed = false;
  }
  if (displayed) {
    while (
      userdetail != null &&
      groupdetail != null &&
      (person1msg != userdetail.length || person2msg != groupdetail.length)
    ) {
      if (person1msg != userdetail.length && person2msg != groupdetail.length) {
        let compare = compareToDates(
          userdetail[person1msg].date,
          groupdetail[person2msg].date
        );
        if (compare) {
          messages += `<div class = "yourChat"><p class = "yourMessage">${userdetail[person1msg].message}</p></div>`;
          person1msg++;
        } else {
          messages += `<div class = "friendChat"><p class = "friendMessage">${groupdetail[person2msg].message}</p></div>`;
          person2msg++;
        }
      } else if (userdetail.length != person1msg) {
        messages += `<div class = "yourChat"><p class = "yourMessage">${userdetail[person1msg].message}</p></div>`;
        person1msg++;
      } else if (groupdetail.length != person2msg) {
        messages += `<div class = "friendChat"><p class = "friendMessage">${groupdetail[person2msg].message}</p></div>`;
        person2msg++;
      }
    }
  }

  messages += "</div>";
  messages += "</div>";
  messages += '<div class = "searchBar">';
  messages += `<input type = "text" class = "searchBarStyle" id = "messagebox" onclick = "getMessages(${i})" placeholder = "Enter Message">`;
  messages += "</div>";
  document.querySelector(".messagesList").innerHTML = messages;
}

function compareToDates(date1, date2) {
  console.log(typeof date1);
  let s1 = date1.split(" ");
  let s2 = s1[0].split("-");
  let s3 = s1[1].split(":");
  let s4 = date2.split(" ");
  let s5 = s4[0].split("-");
  let s6 = s4[1].split(":");
  for (let j = 0; j < 3; j++) {
    if (parseInt(s2[j], 10) > parseInt(s5[j], 10)) {
      return false;
    } else if (parseInt(s2[j], 10) < parseInt(s5[j], 10)) {
      return true;
    }
  }
  for (let j = 0; j < 3; j++) {
    if (parseInt(s3[j], 10) > parseInt(s6[j], 10)) {
      return false;
    } else if (parseInt(s3[j], 10) > parseInt(s6[j], 10)) {
      return true;
    }
  }
  return true;
}
function getMessages(i) {
  let msg = document.getElementById("messagebox");
  msg.addEventListener("keyup", function (event) {
    if (event.keyCode === 13) {
      let text = msg.value;
      if (text.length != 0) {
        document.getElementById("messagebox").value = "";
        // console.log(text);
        addMessage(text, i);
        // text = "";
      }
    }
  });
}

function addMessage(msg, i) {
  // console.log(msg);
  // console.log(i);
  let xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function () {
    if (xhr.readyState == 4 && xhr.status == 200) {
      debugger;
      isGroupChatFirstEntry = true;
      showgroupnames = false;
      groupchatclick();
    }
  };
  xhr.open("POST", "http://localhost:8085/ChatApp/messages", true);
  xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhr.send("message=" + msg + "&groupId=" + groupIds[i]);
}

function addFriend() {
  document.getElementById("centerContentDiv").appendChild =
    '<div class="friendMobileNoDiv"><label class="friendNolabel" for="friendno">Friend Mobile No : </label><input type="number" id="friendno" class="friendno" maxlength="10" required/></div><div class="addFriendButtonDiv"><input type="submit" class="addFriendButton" onclick="addFriendInYourFriendList()" value="ADD"/></div>';
  document.getElementById("centerContentDiv").style = "display:block";
}
function addFriendInYourFriendList() {
  let friendMobileNumber = document.getElementById("friendno").value;
  if (friendMobileNumber.length == 10) {
    document.getElementById("centerContentDiv").style = "display:none";
  } else {
    alert("Wrong Mobile Number!!!");
  }
}
