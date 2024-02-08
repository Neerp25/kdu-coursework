function tipCalculator(bills){
    var tip = new Array()
    for(let i =0;i<bills.length;i++){
        let x=0;
        if(bills[i]<50){
      x = (0.2)*bills[i]
    }
    if(50<=bills[i] && bills[i]<=200){
        x = (0.15)*bills[i]

    }
    if(bills[i]>200){
      x=(0.1)*bills[i]

    }
    console.log(x)
    tip.push(x)
}
return tip
}

const bills = new Array(140,45,280);

let tip = tipCalculator(bills)
console.log("Tips: "+tip)
let Totalamount = new Array();
for(let i =0;i<bills.length;i++){
    Totalamount[i]=bills[i]+tip[i]
}
console.log("Total-Amount: "+Totalamount)