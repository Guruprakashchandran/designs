
var user;
var isPrivateChatFirstEntry = true,isGroupChatFirstEntry = true;
function friendMessage(){

    let word = "guru<br>guru<br>guru<br>";
    let height = 3 * 100;
    document.querySelector(".friendMessage").style = "height : ";
}


function showProfile(){

    alert("Profile Clicked!!!");
}

var names = [];
var groupmessages = [];
function personalchatclick(){

    document.querySelector(".groupChat").style = "background-color : #3e4e53";
    document.querySelector(".privateChat").style = "background-color : white";
    document.querySelector(".usersicon").style = "color : white";
    document.querySelector(".usericon").style = "color : #3e4e53";
    if(isPrivateChatFirstEntry == true){

        isPrivateChatFirstEntry = false;
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function(){

            if(xhr.readyState == 4 && this.status == 200) {

                var privateChats = JSON.parse(this.responseText);
                names = privateChats["friendsName"];
                user = privateChats.userDetails;
                // console.log(user.mobileno);
                displayPersonalFriendsList(names);
            }
        }
        xhr.open("GET","http://localhost:8086/ChatApp/personalchat",true);
        xhr.send();
    }
    else{

        displayPersonalFriendsList(names);
    }
     
}


function displayPersonalFriendsList(names){

	let content= "";
    for(let i = 0;i < names.length;i++){

        content += `<div class = "contact" onclick = "showPersonalMessages(${i})"><span class = "nameContainer">${names[i]}</span></div>`;
    }
    if(content.length == 0){
        content += '<div class = "no_contacts_Container"><Span class = "nocontacts">No Contacts</span></div>';
        document.getElementById("chatLists").innerHTML = content;
    }
    else{
        let searchBar = '<div class = "userSearchDiv"><input type = "text" class = "userSearch" placeholder = "Search Friend"></div>';
        content = searchBar + content;
        document.getElementById("chatLists").innerHTML = content;
    }
}


function showPersonalMessages(i){

    let messages = "";
    messages += '<div class = "messageHeader">'
    messages += `<span class = "chatNameStyle">${names[i]}</span>`
    messages += '<div class = "messages">'
    messages += '<div class = "messagesAdjustment">'
    messages += '<div class = "friendChat"><p class = "friendMessage">guru<br>guru<br>guru<br>guru<br></p></div>';
    messages += '<div class = "yourChat"><p class = "yourMessage">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Cupiditate, molestiae! Eveniet alias, blanditiis laboriosam quasi quis harum recusandae! Aut labore voluptatum autem rerum. Iste sapiente quisquam maiores quam, aut voluptatibus nisi minima. Aut amet pariatur inventore ex, natus est nisi consectetur voluptatem consequuntur porro modi, optio mollitia quas accusamus recusandae?</p></div>'
    messages += '<div class = "friendChat"><p class = "friendMessage">guru<br>guru<br>guru<br>guru<br></p></div>';
    messages += '</div>'
    messages += '</div>'
    messages += '<div class = "searchBar">'
    messages += '<input type = "text" class = "searchBarStyle" placeholder = "Enter Message">'
    messages += '</div>'
    document.querySelector(".messagesList").innerHTML = messages;
    // console.log(messages);
}

var groupnames = [];
function groupchatclick(){
    
    document.querySelector(".groupChat").style = "background-color : white";
    document.querySelector(".privateChat").style = "background-color : #3e4e53";
    document.querySelector(".usericon").style = "color : white";
    document.querySelector(".usersicon").style = "color :#3e4e53";
    if(isGroupChatFirstEntry == true){

        isGroupChatFirstEntry = false;
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function(){

            if(xhr.readyState == 4 && this.status == 200) {

                let groupChats = JSON.parse(this.responseText);
                groupnames = groupChats["groupNames"];
                groupmessages = groupChats.messages;
                
                // messages(messages); 
                // console.log(groupmessages);
                displayGroupList(groupnames);
            }   
        }
        xhr.open("GET","http://localhost:8086/ChatApp/groupchatlist",true);
        xhr.send();
    }
    else{

        displayGroupList(groupnames);
    }
    
}


function displayGroupList(groupnames){
    let content ="";
    for(let i = 0;i < groupnames.length;i++){

        content += `<div class = "contact" onclick = "showGroupMessages(${i})"><span class = "nameContainer">${groupnames[i]}</span></div>`;
    }
   if(content.length == 0){
        content += `<div class = "no_contacts_Container"><Span class = "nocontacts">No Contacts</span></div>`;
        document.getElementById("chatLists").innerHTML = content;
    }
    else{

        let searchBar = '<div class = "userSearchDiv"><input type = "text" class = "userSearch" placeholder = "Search Group"></div>';
        content = searchBar + content;
        document.getElementById("chatLists").innerHTML = content;
    }
}

function messages(groupmessages) {

    for (let i = 0; i < messages.length; i++) {
            
        let msgArr = messages[i];
        for (let j = 0; j < msgArr.length; j++) {
        
            let msgObj = msgArr[j];
            let userdetailArr = msgObj["userdetail"];
            let groupdetail = msgObj["group"];
            // console.log(userdetailArr.length);
            for (let k = 0; k < userdetailArr.length; k++) {
         
                const userdetail = userdetailArr[k];
                const userId = userdetail.message;
                console.log(1);
                console.log(userId);
                console.log(userdetail);
            }
        }
    }
}
function showGroupMessages(i){

        let messages = "";
        messages += '<div class = "messageHeader">'
        messages += `<span class = "chatNameStyle">${groupnames[i]}</span>`
        messages += '</div>'
        messages += '<div class = "messages">'
        messages += '<div class = "messagesAdjustment">'
        let msgArr = groupmessages[i];
        // console.log(groupmessages);
        // console.log(msgArr);
        // console.log(i);
        // for (let j = 0; j < msgArr.length; j++) {
        
            let msgObj = msgArr[i];

            let userdetail = msgObj["userdetail"];
            let groupdetail = msgObj["group"];
            if(userdetail != null){

                for (let k = 0; k < userdetail.length; k++) {
    
                        messages += `<div class = "yourChat"><p class = "yourMessage">${userdetail[k].message}</p></div>`;            
                    }
            }
            if(groupdetail != null){

                for(let k = 0;k<groupdetail.length;++k){

                    messages += `<div class = "friendChat"><p class = "friendMessage">${groupdetail[k].message}</p></div>`;            
               }
            }
        // }
        // }
        // messages += '<div class = "friendChat"><p class = "friendMessage">guru<br>guru<br>guru<br>guru<br></p></div>';
        // messages += '<div class = "yourChat"><p class = "yourMessage">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Cupiditate, molestiae! Eveniet alias, blanditiis laboriosam quasi quis harum recusandae! Aut labore voluptatum autem rerum. Iste sapiente quisquam maiores quam, aut voluptatibus nisi minima. Aut amet pariatur inventore ex, natus est nisi consectetur voluptatem consequuntur porro modi, optio mollitia quas accusamus recusandae?</p></div>'
        // messages += '<div class = "friendChat"><p class = "friendMessage">guru<br>guru<br>guru<br>guru<br></p></div>';
        messages += '</div>'
        messages += '</div>'
        messages += '<div class = "searchBar">'
        messages += `<input type = "text" class = "searchBarStyle" id = "messagebox" onclick = "getMessages(${i})" placeholder = "Enter Message">`
        messages += '</div>'
        document.querySelector(".messagesList").innerHTML = messages;
    //     let xhr = new XMLHttpRequest();
    //     xhr.onreadystatechange = function(){

    //     if(xhr.readyState == 4 && this.status == 200){

    //         console.log(this.responseText);
    //     }
    //     xhr.open("POST","http://localhost:8086/ChatApp/messages",true);
    //     xhr.send();
    // }
}

function getMessages(i) {

    let msg = document.getElementById("messagebox");
    msg.addEventListener("keyup",function(event){

        if(event.keyCode === 13){

            let text = msg.value;
            if(text.length != 0){

                document.getElementById("messagebox").value = "";
                // console.log(text);
                addMessage(text);
                // text = "";
            }
        }
    });
}


function addMessage(msg,i){

    
}