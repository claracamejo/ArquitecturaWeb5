document.querySelector("#add-Client").addEventListener("click", addClient);
document.querySelector("#add-Product").addEventListener("click", addProduct);
document.querySelector("#add-Bill").addEventListener("click", addBill);
//document.querySelector("#bill-product-list").addEventListener("click", addProductosCarrito);
document.querySelector("#edit-Client").addEventListener("click", editClient);
document.querySelector("#edit-Product").addEventListener("click", editProduct);
<<<<<<< HEAD
//document.querySelector("#edit-Bill").addEventListener("click", editBill);
=======
>>>>>>> branch 'main' of https://github.com/tomasmat12/ArquiWebTP4.git

window.addEventListener('DOMContentLoaded', (event) => {
    
	getProducts();
});

let productosCarrito = [];

function addProduct() {
	let name = document.querySelector("#nameP").value;
	let price = document.querySelector("#price").value;
	let stock = document.querySelector("#stock").value;

	if( name === ""|| price === "" || stock === ""){
			return alert("Todos los campos son requeridos");
	}
	
	let data = {
		"name": name,
		"price": price,
		"stock": stock
	}

	fetch('http://localhost:8080/product', {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify(data)
	})
		.then(response => {
			getProducts(); //revisar 
		})
		.catch(function(error) {
			console.log(error);
		})
	}

function addClient() {
	let numDoc = document.querySelector("#dni").value;
	let name = document.querySelector("#nameC").value;
	let lastname = document.querySelector("#lastname").value;
	let address = document.querySelector("#address").value;

	if((numDoc === "" || name === "")||(lastname === "" || address === "")){
			return alert("Todos los campos son requeridos");
			
	}
	
	let data = {
		"dni": numDoc,
		"name": name,
		"lastname": lastname,
		"address": address
	}

	fetch('http://localhost:8080/client', {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify(data)
	})
		.then(response => {
			//getCities(); //revisar 
		})
		.catch(function(error) {
			console.log(error);
		})
	}

function addBill() {
console.log(productosCarrito);
	let numDoc = document.querySelector("#dniC").value;
	let date = document.querySelector("#dateC").value;
	let total = document.querySelector("#totalCarrito").value;
	let carrito = productosCarrito;

	if(numDoc === "" || date === ""){
			return alert("Todos los campos son requeridos");
	}
	
	let data = {
		"dni": numDoc,
		"date": date,
		"total": total,
	    "product": carrito 
	}

return console.log(data);
	fetch('http://localhost:8080/bill', {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify(data)
	})
		.then(response => {
			//getCities(); //revisar 
		})
		.catch(function(error) {
			console.log(error);
		})
	}


function getProducts() {
console.log("getP");
	fetch('http://localhost:8080/product')
		.then(response => {
			return response.json()
		}).then(function(products) {
			
			let bodyTable = document.getElementsByClassName('table')[0];
			let bodyTable2 = document.getElementById('bill-product-list');
			bodyTable2.style.display = "table";
			products.forEach(element => {
				let newRow = bodyTable.insertRow(-1);
				let cell1 = newRow.insertCell(0);
				let newText1 = document.createTextNode(element['id']);
				cell1.appendChild(newText1);
				let cell2 = newRow.insertCell(1);
				let newText2 = document.createTextNode(element['name']);
				cell2.appendChild(newText2);
				let cell3 = newRow.insertCell(2);
				let newText3 = document.createTextNode(element['price']);
				cell3.appendChild(newText3);
				let cell4 = newRow.insertCell(3);
				let newText4 = document.createTextNode(element['stock']);
				cell4.appendChild(newText4);
				let cell5 = newRow.insertCell(4);
				let btn = document.createElement("button");
				btn.innerHTML = "Carrito";
				btn.onclick = function(){
					event.preventDefault();
					let monto = document.getElementById("totalCarrito");
					let cantidad = document.getElementById("prodCarrtio" + element['id']).value;
					let montoT = (cantidad * element['price'])
					monto.value =  montoT + parseInt(monto.value, 10);
					element.quantity = cantidad;
					   productosCarrito.push(element);
				};
				cell5.appendChild(btn);
				let cell6 = newRow.insertCell(4);
				let input = document.createElement("input");
				input.type = "number";
				input.min = 0;
				input.max = 3;
				input.style.width = "50px";
				input.setAttribute("id", "prodCarrtio" + element['id']);
				cell6.appendChild(input);
			})
		})
		.catch(function(error) {
			console.log(error);
		}

		);
}

function getAllClient() {
    let bodyTable = document.getElementsByClassName('bodyTable')[0];
	let bodyTable1 = document.getElementById('myTable');
	bodyTable1.style.display = "table";
	fetch('http://localhost:8080/client')
		.then(response => {
			return response.json()
		}).then(function(response) {
			let elements = response;
			showClients(bodyTable, elements);
		})
		.catch(function(error) {
			console.log(error);
		}

		);
}

function showClients(bodyTable, elements) {
    bodyTable.innerHTML = "";
	elements.forEach(element => {
		let newRow = bodyTable.insertRow(-1);
		let cell1 = newRow.insertCell(0);
		let newText1 = document.createTextNode(element['dni']);
		cell1.appendChild(newText1);
		let cell2 = newRow.insertCell(1);
		let newText2 = document.createTextNode(element['name']);
		cell2.appendChild(newText2);
		let cell3 = newRow.insertCell(2);
		let newText3 = document.createTextNode(element['lastname']);
		cell3.appendChild(newText3);
		let cell4 = newRow.insertCell(3);
		let newText4 = document.createTextNode(element['address']);
		cell4.appendChild(newText4);
		let cell5 = newRow.insertCell(4);
		let btn = document.createElement("button");
		btn.innerHTML = "Eliminar";
		btn.onclick = function(){
			    fetch('http://localhost:8080/client/' + element['dni'], {
			        method: 'DELETE',
			    })
			       .then(response => {
					return response.json()
				}).then(function(response) {
					if(response.error){
						return alert("El cliente tiene compras asociadas, y no se puede eliminar, o se perderia la informacion");
					}
				})
				.catch(function(error) {
					console.log(error);
				})
			};
		cell5.appendChild(btn);
		let cell6 = newRow.insertCell(4);
		let btn2 = document.createElement("button");
		btn2.innerHTML = "Editar";
		btn2.dataset.toggle = "modal";
		btn2.dataset.target = "#editClient";
		btn2.onclick = function(){
				document.getElementById("dniEdit").value = element['dni']
			};
		cell6.appendChild(btn2);
	});
}

function editClient() {
	let numDoc = document.querySelector("#dniEdit").value;
	let name = document.querySelector("#nameCEdit").value;
	let lastname = document.querySelector("#lastnameEdit").value;
	let address = document.querySelector("#addressEdit").value;

	if((numDoc === "" || name === "")||(lastname === "" || address === "")){
			return alert("Todos los campos son requeridos");	
	}
	
	let data = {
		"dni": numDoc,
		"name": name,
		"lastname": lastname,
		"address": address
	}

	fetch('http://localhost:8080/client/'+numDoc, {
		method: 'PUT',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify(data)
	})
		.then(response => {
			//getCities(); //revisar 
		})
		.catch(function(error) {
			console.log(error);
		})
	}
	
function ocultarTablaC(){
	let bodyTable = document.getElementById('myTable');
	bodyTable.style.display = "none";
}

function getAllProducts() {
    let bodyTable = document.getElementsByClassName('bodyTableP')[0];
	let bodyTable1 = document.getElementById('myTableP');
	bodyTable1.style.display = "table";
	fetch('http://localhost:8080/product')
		.then(response => {
			return response.json()
		}).then(function(response) {
			let elements = response;
			showProducts(bodyTable, elements);
		})
		.catch(function(error) {
			console.log(error);
		}

		);
}

function showProducts(bodyTable, elements) {
    bodyTable.innerHTML = "";
	elements.forEach(element => {
		let newRow = bodyTable.insertRow(-1);
		let cell1 = newRow.insertCell(0);
		let newText1 = document.createTextNode(element['id']);
		cell1.appendChild(newText1);
		let cell2 = newRow.insertCell(1);
		let newText2 = document.createTextNode(element['name']);
		cell2.appendChild(newText2);
		let cell3 = newRow.insertCell(2);
		let newText3 = document.createTextNode(element['price']);
		cell3.appendChild(newText3);
		let cell4 = newRow.insertCell(3);
		let newText4 = document.createTextNode(element['stock']);
		cell4.appendChild(newText4);
		let cell5 = newRow.insertCell(4);
		let btn = document.createElement("button");
		btn.innerHTML = "Eliminar";
		btn.onclick = function(){
			    fetch('http://localhost:8080/product/' + element['id'], {
			        method: 'DELETE',
			    })
			       .then(response => {
					return response.json()
				}).then(function(response) {
					if(response.error){
						return alert("El producto tiene compras asociadas, y no se puede eliminar, o se perderia la informacion");
					}
				})
				.catch(function(error) {
					console.log(error);
				})
			};
		cell5.appendChild(btn);
		let cell6 = newRow.insertCell(4);
		let btn2 = document.createElement("button");
		btn2.innerHTML = "Editar";
		btn2.dataset.toggle = "modal";
		btn2.dataset.target = "#editProduct";
		btn2.onclick = function(){
				document.getElementById("idEdit").value = element['id']
			};
		cell6.appendChild(btn2);
	});
}

function editProduct() {
	let id = document.querySelector("#idEdit").value;
	let name = document.querySelector("#namePEdit").value;
	let price = document.querySelector("#priceEdit").value;
	let stock = document.querySelector("#stockEdit").value;

	if((id === "" || name === "")||(price === "" || stock === "")){
			return alert("Todos los campos son requeridos");	
	}
	
	let data = {
		"id": id,
		"name": name,
		"price": price,
		"stock": stock
	}

	fetch('http://localhost:8080/product/'+id, {
		method: 'PUT',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify(data)
	})
		.then(response => {
			//getCities(); //revisar 
		})
		.catch(function(error) {
			console.log(error);
		})
	}
	
function ocultarTablaP(){
	let bodyTable = document.getElementById('myTableP');
	bodyTable.style.display = "none";
}

function getAllBills() {
    let bodyTable = document.getElementsByClassName('bodyTableB')[0];
	let bodyTable1 = document.getElementById('myTableB');
	bodyTable1.style.display = "table";
	fetch('http://localhost:8080/bill')
		.then(response => {
			return response.json()
		}).then(function(response) {
			let elements = response;
			showBills(bodyTable, elements);
		})
		.catch(function(error) {
			console.log(error);
		}

		);
}

function showBills(bodyTable, elements) {
    bodyTable.innerHTML = "";
	elements.forEach(element => {
		let newRow = bodyTable.insertRow(-1);
		let cell1 = newRow.insertCell(0);
		let newText1 = document.createTextNode(element['id']);
		cell1.appendChild(newText1);
		let cell2 = newRow.insertCell(1);
		let newText2 = document.createTextNode(element['client']['dni']);
		cell2.appendChild(newText2);
		let cell3 = newRow.insertCell(2);
		let newText3 = document.createTextNode(element['date']);
		cell3.appendChild(newText3);
		let cell4 = newRow.insertCell(3);
		let newText4 = document.createTextNode(element['total']);
		cell4.appendChild(newText4);
        let cell5 = newRow.insertCell(4);
		let btn2 = document.createElement("button");
		btn2.innerHTML = "Ver Productos";
		btn2.dataset.toggle = "modal";
		btn2.dataset.target = "#billProducts";
		btn2.onclick = function(){
				document.getElementById("idBillProduct").value = element['id'];
				getBillProduct(element['id']);
			};
		cell5.appendChild(btn2);
	});
}

function getBillProduct(id){
let bodyTable = document.getElementsByClassName('bodyTableBillProduct')[0];
let bodyTable1 = document.getElementById('myTableBillProduct');
fetch('http://localhost:8080/billproduct/bill/' + id, {
			        method: 'GET',
			    })
			       .then(response => {
					return response.json()
				}).then(function(response) {
					let elements = response;
			        showBillProduct(bodyTable, elements);
				})
				.catch(function(error) {
					console.log(error);
				})
}

function showBillProduct(bodyTable, elements) {
    bodyTable.innerHTML = "";
	elements.forEach(element => {
		let newRow = bodyTable.insertRow(-1);
		let cell1 = newRow.insertCell(0);
		let newText1 = document.createTextNode(element['product']['name']);
		cell1.appendChild(newText1);
		let cell2 = newRow.insertCell(1);
		let newText2 = document.createTextNode(element['product']['stock']);
		cell2.appendChild(newText2);
		let cell3 = newRow.insertCell(2);
		let newText3 = document.createTextNode(element['product']['price']);
		cell3.appendChild(newText3);
	});
}

function ocultarTablaB(){
	let bodyTable = document.getElementById('myTableB');
	bodyTable.style.display = "none";
}

function getReportClient() {
    let bodyTable = document.getElementsByClassName('bodyTableClient')[0];
	let bodyTable1 = document.getElementById('myTableClient');
	bodyTable1.style.display = "table";
	fetch('http://localhost:8080/client/report')
		.then(response => {
			return response.json()
		}).then(function(response) {
			let elements = response;
			showClientReport(bodyTable, elements);
		})
		.catch(function(error) {
			console.log(error);
		}

		);
}

function showClientReport(bodyTable, elements) {
    bodyTable.innerHTML = "";
	elements.forEach(element => {
		let newRow = bodyTable.insertRow(-1);
		let cell1 = newRow.insertCell(0);
		let newText1 = document.createTextNode(element['dni']);
		cell1.appendChild(newText1);
		let cell2 = newRow.insertCell(1);
		let newText2 = document.createTextNode(element['name']);
		cell2.appendChild(newText2);
		let cell3 = newRow.insertCell(2);
		let newText3 = document.createTextNode(element['lastname']);
		cell3.appendChild(newText3);
		let cell4 = newRow.insertCell(3);
		let newText4 = document.createTextNode(element['address']);
		cell4.appendChild(newText4);
        let cell5 = newRow.insertCell(4);
		let newText5 = document.createTextNode(element['total']);
		cell5.appendChild(newText5);
		let cell6 = newRow.insertCell(5);
	});
}

function ocultarTablaClient(){
	let bodyTable = document.getElementById('myTableClient');
	bodyTable.style.display = "none";
}

function getReportBill() {
    let bodyTable = document.getElementsByClassName('bodyTableBill')[0];
	let bodyTable1 = document.getElementById('myTableBill');
	bodyTable1.style.display = "table";
	fetch('http://localhost:8080/bill/report')
		.then(response => {
			return response.json()
		}).then(function(response) {
			let elements = response;
			showBillReport(bodyTable, elements);
		})
		.catch(function(error) {
			console.log(error);
		}

		);
}

function showBillReport(bodyTable, elements) {
    bodyTable.innerHTML = "";
	elements.forEach(element => {
		let newRow = bodyTable.insertRow(-1);
		let cell1 = newRow.insertCell(0);
		let newText1 = document.createTextNode(element['date']);
		cell1.appendChild(newText1);
		let cell2 = newRow.insertCell(1);
		let newText2 = document.createTextNode(element['total']);
		cell2.appendChild(newText2);
	});
}

function ocultarTablaBill(){
	let bodyTable = document.getElementById('myTableBill');
	bodyTable.style.display = "none";
}

function getReportProduct() {
    let bodyTable = document.getElementsByClassName('bodyTableProduct')[0];
	let bodyTable1 = document.getElementById('myTableProduct');
	bodyTable1.style.display = "table";
	fetch('http://localhost:8080/product/getReport')
		.then(response => {
			return response.json()
		}).then(function(response) {
			let elements = response;
			showProductReport(bodyTable, elements);
		})
		.catch(function(error) {
			console.log(error);
		}

		);
}

function showProductReport(bodyTable, elements) {
    bodyTable.innerHTML = "";
	elements.forEach(element => {
		let newRow = bodyTable.insertRow(-1);
		let cell1 = newRow.insertCell(0);
		let newText1 = document.createTextNode(element['id']);
		cell1.appendChild(newText1);
		let cell2 = newRow.insertCell(1);
		let newText2 = document.createTextNode(element['name']);
		cell2.appendChild(newText2);
		let cell3 = newRow.insertCell(2);
		let newText3 = document.createTextNode(element['price']);
		cell3.appendChild(newText3);
		let cell4 = newRow.insertCell(3);
		let newText4 = document.createTextNode(element['totalSold']);
		cell4.appendChild(newText4);
	});
}

function ocultarTablaProduct(){
	let bodyTable = document.getElementById('myTableProduct');
	bodyTable.style.display = "none";
}

