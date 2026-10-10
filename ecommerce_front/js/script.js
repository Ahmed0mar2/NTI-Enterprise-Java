let x = new XMLHttpRequest();
x.open("GET", "https://fakestoreapi.com/products?limit=10", false);
x.send();

if (x.readyState === 4 && x.status === 200) {
    let products = JSON.parse(x.responseText);
    
    if (!localStorage.getItem('products')) {
        let formattedProducts = products.map(product => ({
            id: product.id.toString(),
            name: product.title,
            image: product.image,
            category: product.category,
            price: product.price,
            description: product.description,
            stock: 10
        }));
        localStorage.setItem('products', JSON.stringify(formattedProducts));
    }
}

let users = JSON.parse(localStorage.getItem('users')) || [];
if (users.length === 0) {
    users.push({
        id: '1',
        name: 'Admin',
        email: 'admin@shop.com',
        password: '123',
        role: 'admin'
    });
    localStorage.setItem('users', JSON.stringify(users));
}