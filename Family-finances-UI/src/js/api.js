function setUserName(name) {
    localStorage.setItem('userName', name);
}

function getUserName() {
    return localStorage.getItem('userName');
}

function setRoomName(name) {
    localStorage.setItem('roomName', name);
}

function getRoomName() {
    return localStorage.getItem('roomName');
}

function login(name, password) {
    return new Promise(
        (resolve, reject) => {
            $.ajax({
                url: 'http://localhost:8081/login',
                type: "GET",
                dataType: "json",
                data: {name: name, password: password},
                beforeSend: function(xhr) { 
                    xhr.setRequestHeader("Content-Type", "application/json");
                },
                success: function(data){
                    console.log("success " + data);
                    if (!data) {
                        reject(data);
                    }
                    resolve(data);
                },
                error:function(data) {
                    console.log("error " + data);
                    reject(data);
                }
            })
        }
    );
}

function registration(name, password, date) {
    return new Promise(
        (resolve, reject) => {
            $.ajax({
                url:  'http://localhost:8081/registration',
                type: "GET",
                dataType: "json",
                data: {name: name, password: password, date: date},
                beforeSend: function(xhr) { 
                    xhr.setRequestHeader("Content-Type", "application/json");
                },
                success: function(data){
                    console.log("success " + (data == 'true'));
                    if (!data) {
                        reject(data);
                    }
                    resolve(data);
                },
                error:function(data) {
                    console.log("error " + data.data);
                    reject(data.data);
                }
            })
        }
    );
}

function getUserRooms(user) {
    return new Promise (
        (resolve, reject) => {
            $.ajax({
            url:  'http://localhost:8081/room',
            type: "GET",
            dataType: "json",
            data: {name: user},
            beforeSend: function(xhr) { 
                xhr.setRequestHeader("Content-Type", "application/json");
                },
            success: function(data){
                resolve(data);
            },
            error:function(data){
                reject(data);
            }
        })
    });
}

function createRoom(name, password, userName) {
    return new Promise (
        (resolve, reject) => {
            $.ajax({
            url:  'http://localhost:8081/room/create',
            type: "GET",
            dataType: "json",
            data: {name: name, password: password, userName: userName},
            beforeSend: function(xhr) { 
                xhr.setRequestHeader("Content-Type", "application/json");
                },
            success: function(data){
                resolve(data);
            },
            error:function(data){
                reject(data);
            }
        })
    });
}

function enterRoom(name, password, userName) {
    return new Promise (
        (resolve, reject) => {
            $.ajax({
            url:  'http://localhost:8081/room/enter',
            type: "GET",
            dataType: "json",
            data: {name: name, password: password, userName: userName},
            beforeSend: function(xhr) { 
                xhr.setRequestHeader("Content-Type", "application/json");
                },
            success: function(data){
                resolve(data);
            },
            error:function(data){
                reject(data);
            }
        })
    });
}

function deleteRoom(name) {
    return new Promise (
        (resolve, reject) => {
            $.ajax({
            url:  'http://localhost:8081/room/delete',
            type: "GET",
            dataType: "json",
            data: {name: name},
            beforeSend: function(xhr) { 
                xhr.setRequestHeader("Content-Type", "application/json");
                },
            success: function(data){
                if (!data) {
                    reject(data);
                }
                resolve(data);
            },
            error:function(data){
                reject(data);
            }
        })
    });
}

function getCategories(roomName) {
    return new Promise (
        (resolve, reject) => {
            $.ajax({
            url:  'http://localhost:8081/finance/categories',
            type: "GET",
            dataType: "json",
            data: {roomName: roomName},
            beforeSend: function(xhr) { 
                xhr.setRequestHeader("Content-Type", "application/json");
                },
            success: function(data){
                resolve(data);
            },
            error:function(data){
                reject(data);
            }
        })
    });
}

function getTypes() {
    return new Promise (
        (resolve, reject) => {
            $.ajax({
            url:  'http://localhost:8081/finance/types',
            type: "GET",
            dataType: "json",
            beforeSend: function(xhr) { 
                xhr.setRequestHeader("Content-Type", "application/json");
                },
            success: function(data){
                resolve(data);
            },
            error:function(data){
                reject(data);
            }
        })
    });
}

function getBudget(roomName) {
    return new Promise (
        (resolve, reject) => {
            $.ajax({
            url:  'http://localhost:8081/room/budget',
            type: "GET",
            dataType: "json",
            data: {roomName: roomName},
            beforeSend: function(xhr) { 
                xhr.setRequestHeader("Content-Type", "application/json");
                },
            success: function(data){
                resolve(data);
            },
            error:function(data){
                reject(data);
            }
        })
    });
}

function updateBudgetApi(roomName, userName, count) {
    return new Promise (
        (resolve, reject) => {
            $.ajax({
            url:  'http://localhost:8081/room/budget/update',
            type: "GET",
            dataType: "json",
            data: {roomName: roomName, userName: userName, count: count},
            beforeSend: function(xhr) { 
                xhr.setRequestHeader("Content-Type", "application/json");
                },
            success: function(data){
                if (!data) {
                    reject(data);
                }
                resolve(data);
            },
            error:function(data){
                reject(data);
            }
        })
    });
}

function createFinance(
    roomName,
    userName,
    categoryName,
    typeName,
    amount,
    date,
    comment
) {
    return new Promise (
        (resolve, reject) => {
            $.ajax({
            url:  'http://localhost:8081/finance/create',
            type: "GET",
            dataType: "json",
            data: {roomName: roomName, userName: userName, categoryName: categoryName, typeName: typeName,
                amount: amount, date: date, comment: comment},
            beforeSend: function(xhr) { 
                xhr.setRequestHeader("Content-Type", "application/json");
                },
            success: function(data){
                if (data.length == 0 || !data) {
                    reject(data);
                }
                resolve(data);
            },
            error:function(data){
                reject(data);
            }
        })
    });
}

function getConsumptionByRoom(name) {
    return new Promise (
        (resolve, reject) => {
            $.ajax({
            url:  'http://localhost:8081/finance',
            type: "GET",
            dataType: "json",
            data: {roomName: name, typeName: "Расход"},
            beforeSend: function(xhr) { 
                xhr.setRequestHeader("Content-Type", "application/json");
                },
            success: function(data){
                resolve(data);
            },
            error:function(data){
                reject(data);
            }
        })
    });
}

function getIncomeByRoom(name) {
    return new Promise (
        (resolve, reject) => {
            $.ajax({
            url:  'http://localhost:8081/finance',
            type: "GET",
            dataType: "json",
            data: {roomName: name, typeName: "Доход"},
            beforeSend: function(xhr) { 
                xhr.setRequestHeader("Content-Type", "application/json");
                },
            success: function(data){
                resolve(data);
            },
            error:function(data){
                reject(data);
            }
        })
    });
}

function getIncomeByRoomFormated(name) {
    return new Promise (
        (resolve, reject) => {
            $.ajax({
            url:  'http://localhost:8081/finance/formated',
            type: "GET",
            dataType: "json",
            data: {roomName: name, typeName: "Доход"},
            beforeSend: function(xhr) { 
                xhr.setRequestHeader("Content-Type", "application/json");
                },
            success: function(data){
                resolve(data);
            },
            error:function(data){
                reject(data);
            }
        })
    });
}

function getConsumptionByRoomFormated(name) {
    return new Promise (
        (resolve, reject) => {
            $.ajax({
            url:  'http://localhost:8081/finance/formated',
            type: "GET",
            dataType: "json",
            data: {roomName: name, typeName: "Расход"},
            beforeSend: function(xhr) { 
                xhr.setRequestHeader("Content-Type", "application/json");
                },
            success: function(data){
                resolve(data);
            },
            error:function(data){
                reject(data);
            }
        })
    });
}

function getDataIncomeByTime(name, date) {
    return new Promise (
        (resolve, reject) => {
            $.ajax({
            url:  'http://localhost:8081/finance/date',
            type: "GET",
            dataType: "json",
            data: {roomName: name, typeName: "Доход", date: date},
            beforeSend: function(xhr) { 
                xhr.setRequestHeader("Content-Type", "application/json");
                },
            success: function(data){
                resolve(data);
            },
            error:function(data){
                reject(data);
            }
        })
    });
}

function getDataConsumptionByTime(name, date) {
    return new Promise (
        (resolve, reject) => {
            $.ajax({
            url:  'http://localhost:8081/finance/date',
            type: "GET",
            dataType: "json",
            data: {roomName: name, typeName: "Расход", date: date},
            beforeSend: function(xhr) { 
                xhr.setRequestHeader("Content-Type", "application/json");
                },
            success: function(data){
                resolve(data);
            },
            error:function(data){
                reject(data);
            }
        })
    });
}

function getDataConsumptionAnalysis(name) {
    return new Promise (
        (resolve, reject) => {
            $.ajax({
            url:  'http://localhost:8081/finance/analysis',
            type: "GET",
            dataType: "json",
            data: {roomName: name},
            beforeSend: function(xhr) { 
                xhr.setRequestHeader("Content-Type", "application/json");
                },
            success: function(data){
                resolve(data);
            },
            error:function(data){
                reject(data);
            }
        })
    });
}

function addCategoryForRoom(name, userName, category) {
    return new Promise (
        (resolve, reject) => {
            $.ajax({
            url:  'http://localhost:8081/finance/categories/create',
            type: "GET",
            dataType: "json",
            data: {roomName: name, userName: userName, categoryName: category},
            beforeSend: function(xhr) { 
                xhr.setRequestHeader("Content-Type", "application/json");
                },
            success: function(data) {
                if (!data) {
                    reject(data);
                }
                resolve(data);
            },
            error:function(data){
                reject(data);
            }
        })
    });
}


// Сырая, нужна обработка
function getAllFinancesByRoom(name) {
    return new Promise (
        (resolve, reject) => {
            $.ajax({
            url:  'http://localhost:8081/finance/all',
            type: "GET",
            dataType: "json",
            data: {roomName: name},
            beforeSend: function(xhr) { 
                xhr.setRequestHeader("Content-Type", "application/json");
                },
            success: function(data){
                resolve(data);
            },
            error:function(data){
                reject(data);
            }
        })
    });
}
