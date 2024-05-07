<?php
// Database connection parameters
$host = 'localhost';
$username = 'esbd_root';
$password = 'ROOTNEXT##@@12';
$database = 'esbd_data';

// Establish connection to MySQL database
$con = new mysqli($host, $username, $password, $database);

// Check if the connection failed and exit if it did
if ($con->connect_error) {
    echo "Failed to connect to MySQL: " . $con->connect_error;
    exit();
}

// Get the search query from a request (e.g., from $_GET or $_POST)
$searchQuery = $_GET['search']; // Assuming input is coming from a GET request

// Prepare the SQL statement for execution for search
$sql = "SELECT * FROM user_table WHERE name LIKE CONCAT('%', ?, '%') OR email LIKE CONCAT('%', ?, '%') OR phone LIKE CONCAT('%', ?, '%') OR created_at LIKE CONCAT('%', ?, '%') OR updated_at LIKE CONCAT('%', ?, '%')";
$stmt = $con->prepare($sql);

// Exit if the statement preparation fails
if (!$stmt) {
    echo "Error preparing statement: " . $con->error;
    exit();
}

// Bind parameters to the prepared statement
$stmt->bind_param("sssss", $searchQuery, $searchQuery, $searchQuery, $searchQuery, $searchQuery);

// Execute the statement and check for errors
if ($stmt->execute()) {
    // Fetch the result
    $result = $stmt->get_result();

    // Initialize an array to store the fetched data
    $users = [];

    // Check if there are records in the result set
    if ($result->num_rows > 0) {
        // Fetch each row and add to the users array
        while($row = $result->fetch_assoc()) {
            $users[] = $row;
        }

        // Output the data as JSON
        echo json_encode($users);
    } else {
        echo json_encode(["message" => "No records found"]);
    }
} else {
    echo json_encode(["error" => "Error retrieving records: " . $stmt->error]);
}

// Close the statement and the connection
$stmt->close();
$con->close();
?>
