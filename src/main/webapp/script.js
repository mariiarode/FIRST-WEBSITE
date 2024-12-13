const months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December' ];

function functions() {
    let news1 = document.getElementById("news1");
    let news2 = document.getElementById("news2");
    news1.innerHTML = welcome()+"<br/>"+date()+"<br/>";
    news2.innerHTML = "Author has " + String(returnYears()) + "th birthday " + daysToBirthday() + "<br/> (" + String(months[birthdayMonth-1]) + " " + String(birthdayDay) + " " + String(birthdayYear) + ")";
}

function welcome() {
    let today = new Date();
    let hour = today.getHours();
    if( (hour<18) && (hour>6) ) {
        return 'Good morning, ';
    } else {
        return 'Good evening, ';
    }
}

function date() {
    let today = new Date();
    let day =  today.getDate();
    let month = months[today.getMonth()];
    let year = today.getFullYear();

    return 'today is '+ month + ' ' +  day + ' ' + ' '  + year + '.';
}

let birthdayDay = 27;
let birthdayMonth = 4;
let birthdayYear = 1997;

function daysToBirthday() {
    let currentDate = new Date();
    let currentYear = currentDate.getFullYear();
    let birthdayString = "";
    let timeDiff = 0;
    let daysDiff = 0;
    let nextBirthday = new Date(currentYear, birthdayMonth - 1, birthdayDay);
    if (nextBirthday < currentDate) {
        timeDiff = currentDate.getTime() - nextBirthday.getTime();
        daysDiff = Math.ceil(timeDiff / (1000 * 3600 * 24));
        birthdayString = String(daysDiff) + " days ago";
    } else {
        timeDiff = nextBirthday.getTime() - currentDate.getTime();
        daysDiff = Math.ceil(timeDiff / (1000 * 3600 * 24));
        birthdayString = "in " + String(daysDiff) + " days";
    }

    return birthdayString;
}

function returnYears() {
    let currentDate = new Date();
    let currentYear = currentDate.getFullYear();
    let years = currentYear - birthdayYear;
    return years;
}

function clock() {
    let date = new Date();
    let hour = date.getHours();
    let minute = date.getMinutes();
    let second = date.getSeconds();

    if (minute<10) minute = "0"+minute;
    if(second<10) second = "0"+second;
    let footer = document.getElementById("footer");
    footer.innerHTML="&copy;2024 DG | "+hour+":"+minute+":"+second;

}