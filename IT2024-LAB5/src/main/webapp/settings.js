var saveButton = document.getElementById('save');

// Agrega un evento de clic al botón de guardar
saveButton.addEventListener('click', function() {
    // Obtén el elemento del mensaje
    var messageElement = document.getElementById('message');

    // Muestra el mensaje en el elemento del mensaje
    messageElement.textContent = "Settings have been saved correctly";
});