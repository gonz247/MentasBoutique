let clientsList = [];
async function getClients() {
  const response = await fetch("https://mentasboutique.onrender.com/admin/users");
  const data = await response.json();
  clientsList = data;
}

async function showUsers(){
    await getClients();
    console.log(clientsList);
// Get a reference to the table element
const table = document.getElementById('clientsTable');
const tbody= table.querySelector('tbody')
// Iterate over the clients list and generate rows for each client
clientsList.forEach(client => {
    // Create a new row element
    const row = document.createElement('tr');

    // Create table data cells for each attribute of the client
    Object.values(client).slice(0, 5).forEach(value => {
        const cell = document.createElement('td');
        cell.textContent = value;
        row.appendChild(cell);
    });

    const btnCell = document.createElement('td');
    const btnDelete = document.createElement('button');
    btnDelete.classList.add('btn')
    btnDelete.classList.add('btn-danger')
    btnDelete.classList.add('btn-delete')
    btnDelete.textContent = 'Eliminar';
    btnCell.appendChild(btnDelete);
    row.appendChild(btnCell);

    // Append the row to the table
    tbody.appendChild(row);
});

const deleteButtons = document.querySelectorAll('.btn-delete');

// Iterate over each delete button and add an event listener
deleteButtons.forEach(button => {
    button.addEventListener('click', () => {
        // Get the parent <tr> element of the delete button
        const parentTR = button.closest('tr');
        const modal = createModal();
        document.body.appendChild(modal);
        modal.style.display = 'flex';
        modal.addEventListener('click', function (event) {
            
            if (event.target.id === 'btn-confirm-yes') {
                parentTR.remove();
                modal.remove()
            } else modal.remove()

        });
    });
})


function createModal() {
    const modal = document.createElement('div');
    modal.className = 'modal';
    modal.innerHTML = `
      <div class="modal-content">
        <p class='modal-text'>Confirmar</p>
        <button class="btn btn-success" id="btn-confirm-yes">Sí</button>
        <button class="btn btn-danger" id="btn-confirm-no">No</button>
      </div>
    `;
    return modal;
}

}
showUsers();