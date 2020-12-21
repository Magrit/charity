document.addEventListener("DOMContentLoaded", function () {

    class Donation {

        setCategories(value) {
            this._categories = value;
        }
        setQuantity(value) {
            this._quantity = value;
        }
        setInstitution(value) {
            this._institution = value;
        }
        setStreet(value) {
            this._street = value;
        }
        setCity(value) {
            this._city = value;
        }
        setZipCode(value) {
            this._zipCode = value;
        }
        setPhone(value) {
            this._phone = value;
        }
        setDate(value) {
            this._date = value;
        }
        setTime(value) {
            this._time = value;
        }
        setComment(value) {
            this._comment = value;
        }

        getCategories() {
            return this._categories;
        }
        getQuantity() {
            return this._quantity;
        }
        getInstitution() {
            return this._institution;
        }
        getStreet() {
            return this._street;
        }
        getCity() {
            return this._city;
        }
        getZipCode() {
            return this._zipCode;
        }
        getPhone() {
            return this._phone;
        }
        getDate() {
            return this._date;
        }
        getTime() {
            return this._time;
        }
        getComment() {
            return this._comment;
        }
    }

    const firstButton = document.querySelector("#first");
    const secondButton = document.querySelector("#second");
    const thirdButton = document.querySelector("#third");
    const lastButton = document.querySelector("#last");

    const selectCategories = [];
    const donation = new Donation();

    firstButton.addEventListener('click', evt => {
        const categories = document.querySelectorAll("#categories");
        categories.forEach(category => {
            if (category.checked) {
                selectCategories.push(category.nextElementSibling.nextElementSibling.innerText);
            }
        })
        donation.setCategories(selectCategories);
    })

    secondButton.addEventListener('click', evt => {
        const quantity = document.querySelector("#quantity");
        donation.setQuantity(quantity.value);
    })

    const selectInstitution = [];

    thirdButton.addEventListener('click', evt => {
        const institutions = document.querySelectorAll("#institutions");
        institutions.forEach(institution => {
            if (institution.checked) {
                selectInstitution.push(institution.nextElementSibling.nextElementSibling.firstElementChild.innerText);
            }
        })
        donation.setInstitution(selectInstitution);
    })

    lastButton.addEventListener('click', evt => {
        const street = document.querySelector("#street");
        const city = document.querySelector("#city");
        const zipCode = document.querySelector("#zipCode");
        const phone = document.querySelector("#phone");
        const date = document.querySelector("#date");
        const time = document.querySelector("#time");
        const comment = document.querySelector("#comment");

        donation.setStreet(street.value);
        donation.setCity(city.value);
        donation.setZipCode(zipCode.value);
        donation.setPhone(phone.value);
        donation.setDate(date.value);
        donation.setTime(time.value);
        if (comment.value === "") {
            donation.setComment("Brak uwag");
        } else {
            donation.setComment(comment.value);
        }

        const firstUl = document.querySelector("#firstUl");
        const secondUl = document.querySelector("#secondUl");
        const thirdUl = document.querySelector("#thirdUl");

        let liElement = document.createElement('span');
        liElement.innerHTML = `<li id="firstLi">
            <span class="icon icon-bag"></span>
            <span class="summary--text">${donation.getQuantity()} worki ${donation.getCategories()}</span>
        </li>
        <li>
            <span class="icon icon-hand"></span>
            <span class="summary--text">dla fundacji ${donation.getInstitution()[0]}</span>
        </li>`;
        firstUl.appendChild(liElement)

        let secondLiElement = document.createElement('span');
        secondLiElement.innerHTML = `<li>${donation.getStreet()}</li>
                                <li>${donation.getCity()}</li>
                                <li>${donation.getZipCode()}</li>
                                <li>${donation.getPhone()}</li>`;
        secondUl.appendChild(secondLiElement);

        let thirdLiElement = document.createElement('span');
        thirdLiElement.innerHTML = `<li>${donation.getDate()}</li>
                                <li>${donation.getTime()}</li>
                                <li>Uwagi dla kuriera: ${donation.getComment()}</li>`;
        thirdUl.appendChild(thirdLiElement);

        const prevButton = document.querySelector("#prev");
        prevButton.addEventListener('onclick', evt1 => {
            for (let child of firstUl.children) {
                child.remove();
            }
            for (let child of secondUl.children) {
                child.remove()
            }
            for (let child of thirdUl.children) {
                child.remove();
            }
        })
    })


})
