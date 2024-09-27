const inputString = '{"firstName":"Alex","lastName":"Hunter","email":"alex@yahoo.com","age":24, "city":"london", "country":"england"}';


 const jsonObject = JSON.parse(inputString);

for(const temp in jsonObject){
    if (temp === "email") {
        continue;
    }
    var demostring = String(jsonObject[temp]);
    demostring = demostring.toUpperCase();
    jsonObject[temp] = demostring;
}

console.log(jsonObject)
delete jsonObject.email

const jsonstringwithoutemail = JSON.stringify(jsonObject)
console.log(jsonstringwithoutemail)