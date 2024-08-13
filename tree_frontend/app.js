const express = require("express");

const app = express();

app.set('view engine', 'ejs');
app.use(express.urlencoded({extended:true}));
app.use(express.static('public'));

app.get('/', (request, response) => {
    response.render('index');
})

app.post('/', async(request, response) => {
    const fetch = await import('node-fetch');
    if(request.body.values != null && request.body.perfect != null){
        var array = request.body.values.split(",");
        for(element in array){
            array[element] = parseInt(array[element], 10);
        }
        var boolean;
        if(request.body.perfect == "true"){
            boolean = true;
        } else {
            boolean = false;
        }
        var result = await fetch.default("http://localhost:8080/api/tree/create", {
            method: 'POST',
            body: JSON.stringify({
                numbers: array,
                perfect: boolean
            }),
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        }).then(response => response.json()).then(data => {
            if(data != null){
                response.render('createResultPage', {tree : JSON.stringify(data, null, 2)});
            } else {
                response.render('error');
            }
        });
    } else {
        response.render('error');
    }
})

app.get('/previous-trees', async(request, response) => {
    const fetch = await import('node-fetch');
    var result = await fetch.default("http://localhost:8080/api/tree/all", {
        method: 'GET',
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    }).then(response => response.json()).then(data => {
        if(data != null){
            response.render('previousTreePage', {trees : JSON.stringify(data, null, 2)});
        } else {
            response.render('error');
        }
    });
})

app.get('/previous-trees/:id', async(request, response) => {
    const fetch = await import('node-fetch');
    var result = await fetch.default(`http://localhost:8080/api/tree/${request.params.id}`, {
        method: 'GET',
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    }).then(response => response.json()).then(data => {
        if(data != null){
            response.render('previousTreePage', {trees : JSON.stringify(data, null, 2)});
        } else {
            response.render('error');
        }
    });
})

app.get('/previous-trees/detailed', async(request, response) => {
    const fetch = await import('node-fetch');
    var result = await fetch.default("http://localhost:8080/api/tree/detailed/all", {
        method: 'GET',
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    }).then(response => response.json()).then(data => {
        if(data != null){
            response.render('detailedPreviousTreePage', {trees : JSON.stringify(data, null, 2)});
        } else {
            response.render('error');
        }
    });
})

app.get('/previous-trees/detailed/:id', async(request, response) => {
    const fetch = await import('node-fetch');
    var result = await fetch.default(`http://localhost:8080/api/tree/detailed/${request.params.id}`, {
        method: 'GET',
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    }).then(response => response.json()).then(data => {
        if(data != null){
            response.render('detailedPreviousTreePage', {trees : JSON.stringify(data, null, 2)});
        } else {
            response.render('error');
        }
    });
})

app.use((request, response) => {
    response.status(404).write('404 - route not found.');
    response.end();
}) 

//the three lines of code that sets up the server
app.listen(3000, () => {
    console.log(`Simple app running on port 3000.`)
});