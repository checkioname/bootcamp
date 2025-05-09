package handler

import (
	"app/internal"
	"encoding/json"
	"net/http"

	"github.com/bootcamp-go/web/response"
)

// VehicleJSON is a struct that represents a vehicle in JSON format
type VehicleJSON struct {
	ID              int     `json:"id"`
	Brand           string  `json:"brand"`
	Model           string  `json:"model"`
	Registration    string  `json:"registration"`
	Color           string  `json:"color"`
	FabricationYear int     `json:"year"`
	Capacity        int     `json:"passengers"`
	MaxSpeed        float64 `json:"max_speed"`
	FuelType        string  `json:"fuel_type"`
	Transmission    string  `json:"transmission"`
	Weight          float64 `json:"weight"`
	Height          float64 `json:"height"`
	Length          float64 `json:"length"`
	Width           float64 `json:"width"`
}

// NewVehicleDefault is a function that returns a new instance of VehicleDefault
func NewVehicleDefault(sv internal.VehicleService) *VehicleDefault {
	return &VehicleDefault{sv: sv}
}

// VehicleDefault is a struct with methods that represent handlers for vehicles
type VehicleDefault struct {
	// sv is the service that will be used by the handler
	sv internal.VehicleService
}

// GetAll is a method that returns a handler for the route GET /vehicles
func (h *VehicleDefault) GetAll() http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		// request
		// ...

		// process
		// - get all vehicles
		v, err := h.sv.FindAll()
		if err != nil {
			response.JSON(w, http.StatusInternalServerError, nil)
			return
		}

		// response
		data := make(map[int]VehicleJSON)
		for _, value := range v {
			data[value.Id] = VehicleJSON{
				ID:              value.Id,
				Brand:           value.Brand,
				Model:           value.Model,
				Registration:    value.Registration,
				Color:           value.Color,
				FabricationYear: value.FabricationYear,
				Capacity:        value.Capacity,
				MaxSpeed:        value.MaxSpeed,
				FuelType:        value.FuelType,
				Transmission:    value.Transmission,
				Weight:          value.Weight,
				Height:          value.Height,
				Length:          value.Length,
				Width:           value.Width,
			}
		}
		response.JSON(w, http.StatusOK, map[string]any{
			"message": "success",
			"data":    data,
		})
	}
}

func (h *VehicleDefault) Add() http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		vehicle := VehicleJSON{}
		json.NewDecoder(r.Body).Decode(&vehicle)
		r.Body.Close()

		v := ToVehicle(vehicle)

		err := h.sv.Add(*v)
		if err != nil {
			response.JSON(w, http.StatusInternalServerError, "Houve um erro interno"+err.Error())
			return
		}

		response.JSON(w, http.StatusOK, map[string]any{
			"message": "success",
			"data":    v,
		})
	}
}

func ToVehicle(vehicle VehicleJSON) *internal.Vehicle {
	v := &internal.Vehicle{
		Id: vehicle.ID,
	}
	v.VehicleAttributes.Brand = vehicle.Brand
	v.VehicleAttributes.Model = "juketil"
	v.VehicleAttributes.Registration = vehicle.Registration
	v.VehicleAttributes.Color = vehicle.Color
	v.VehicleAttributes.FabricationYear = vehicle.FabricationYear
	v.VehicleAttributes.Capacity = vehicle.Capacity
	v.VehicleAttributes.MaxSpeed = vehicle.MaxSpeed
	v.VehicleAttributes.FuelType = vehicle.FuelType
	v.VehicleAttributes.Transmission = vehicle.Transmission
	v.VehicleAttributes.Weight = vehicle.Weight
	v.VehicleAttributes.Height = vehicle.Height
	v.VehicleAttributes.Length = vehicle.Length
	v.VehicleAttributes.Width = vehicle.Width
	return v
}
