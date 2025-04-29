package internal

//import "app/internal/handler"

// VehicleRepository is an interface that represents a vehicle repository
type VehicleRepository interface {
	// FindAll is a method that returns a map of all vehicles
	FindAll() (v map[int]Vehicle, err error)
	AddVehicle(v Vehicle) error
	//Add(v handler.VehicleJSON) (err error)
}
