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




    const form = document.querySelector("#form");
    const firstButton = document.querySelector("#first");
    const secondButton = document.querySelector("#second");
    const thirdButton = document.querySelector("#third");
    const lastButton = document.querySelector("#last");

    const selectCategories = [];
    const donation = new Donation();

    firstButton.addEventListener('click', evt => {
        const categories = document.querySelectorAll("[categories]");
        categories.forEach(category => {
            if (category.checked) {
                selectCategories.push(category.value);
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
        const institutions = document.querySelectorAll("[institutions]");
        institutions.forEach(institution => {
            if (institution.checked){
                selectInstitution.push(institution.value);
            }
        })
        donation.setInstitution(selectInstitution);
    })

    lastButton.addEventListener('click', evt => {
        const street = document.querySelector("#street");
        const city  = document.querySelector("#city");
        const zipCode  = document.querySelector("#zipCode");
        const phone  = document.querySelector("#phone");
        const date  = document.querySelector("#date");
        const time  = document.querySelector("#time");
        const comment  = document.querySelector("#comment");

        donation.setStreet(street.value);
        donation.setCity(city.value);
        donation.setZipCode(zipCode.value);
        donation.setPhone(phone.value);
        donation.setDate(date.value);
        donation.setTime(time.value);
        donation.setComment(comment.value);
    })


})
