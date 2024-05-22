import React from 'react';
import './AdminDashboard.css'; // Import the CSS file
import Navbar from '../navbar/Navbar'; // Correct import statement
import { FaEdit, FaTrash } from 'react-icons/fa'; // Import icons from react-icons
import { useNavigate } from 'react-router-dom';

const cardData = [
  {
    title: 'Card 1',
    type: 'Type 1',
    description: 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Repudiandae voluptas ullam aut incidunt minima.',
    imgSrc: 'https://th.bing.com/th/id/OIP.9WOXwaEXCzFDxPhkRsqPTAHaHa?rs=1&pid=ImgDetMain'
  },

  {
    title: 'Card 1',
    type: 'Type 1',
    description: 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Repudiandae voluptas ullam aut incidunt minima.',
    imgSrc: 'data:image/jpeg;base64,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'
  },
  {
    title: 'Card 1',
    type: 'Type 1',
    description: 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Repudiandae voluptas ullam aut incidunt minima.',
    imgSrc: 'https://miro.medium.com/v2/resize:fit:558/1*DarP8WkVE8xQwvn6yEqB_w.png'
  },
  {
    title: 'Card 2',
    type: 'Type 2',
    description: 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Repudiandae voluptas ullam aut incidunt minima.',
    imgSrc: 'https://www.kindpng.com/picc/m/355-3559027_c-programming-language-logo-clipart-png-download-c.png'
  },
  {
    title: 'Card 3',
    type: 'Type 3',
    description: 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Repudiandae voluptas ullam aut incidunt minima.',
    imgSrc: 'https://codinglap.com/wp-content/uploads/2024/01/How-to-Switch-Between-Java-Version-on-Ubuntu-Linux-728x437.jpg'
  },
  {
    title: 'Card 4',
    type: 'Type 4',
    description: 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Repudiandae voluptas ullam aut incidunt minima.',
    imgSrc: 'https://pannacci.com/wp-content/uploads/2023/04/JavaScript-logo.png'
  },
  {
    title: 'Card 5',
    type: 'Type 5',
    description: 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Repudiandae voluptas ullam aut incidunt minima.',
    imgSrc: 'https://th.bing.com/th/id/OIP.KdeZuO9WHpjaJ7QXJJX-oAAAAA?rs=1&pid=https://th.bing.com/th/id/OIP.m8nFrqg3AiItcY0UCSYZSgHaE8?w=270&h=180&c=7&r=0&o=5&pid=1.7'
  },
  {
    title: 'Card 1',
    type: 'Type 1',
    description: 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Repudiandae voluptas ullam aut incidunt minima.',
    imgSrc: 'https://miro.medium.com/v2/resize:fit:558/1*DarP8WkVE8xQwvn6yEqB_w.png'
  },
  {
    title: 'Card 1',
    type: 'Type 1',
    description: 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Repudiandae voluptas ullam aut incidunt minima.',
    imgSrc: 'https://miro.medium.com/v2/resize:fit:558/1*DarP8WkVE8xQwvn6yEqB_w.png'
  },
  {
    title: 'Card 1',
    type: 'Type 1',
    description: 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Repudiandae voluptas ullam aut incidunt minima.',
    imgSrc: 'https://miro.medium.com/v2/resize:fit:558/1*DarP8WkVE8xQwvn6yEqB_w.png'
  },  {
    title: 'Card 1',
    type: 'Type 1',
    description: 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Repudiandae voluptas ullam aut incidunt minima.',
    imgSrc: 'https://miro.medium.com/v2/resize:fit:558/1*DarP8WkVE8xQwvn6yEqB_w.png'
  },  {
    title: 'Card 1',
    type: 'Type 1',
    description: 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Repudiandae voluptas ullam aut incidunt minima.',
    imgSrc: 'https://miro.medium.com/v2/resize:fit:558/1*DarP8WkVE8xQwvn6yEqB_w.png'
  },
  {
    title: 'Card 6',
    type: 'Type 6',
    description: 'Lorem ipsum, dolor sit amet consectetur adipisicing elit. Repudiandae voluptas ullam aut incidunt minima.',
    imgSrc: 'https://th.bing.com/th/id/OIP.m8nFrqg3AiItcY0UCSYZSgHaE8?w=270&h=180&c=7&r=0&o=5&pid=1.7'
  }
];

export default function AdminDashboard() {  const navigate = useNavigate();

  const handleNavigation = (path, event) => {
    event.preventDefault();
    navigate(path);
  };

  

  return (
    <>
   
      <div className='class'>
        

  <Navbar />
        <div className="container">
          <div className="dashboard1">
            {cardData.map((card, index) => (
              <article key={index} className="card">
                <div className="card-img">
                  <img src={card.imgSrc} alt={`Card ${index + 1}`} className="card-imgs" />
                </div>
                <div className="project-info">
                  <div className="flex">
                    <div className="project-title">{card.title}</div>
                    <span className="tag">{card.type}</span>
                  </div>
                  <span className="lighter">{card.description}</span>
                </div>
                <div className="card-buttons">
                  <button>
                    <FaTrash className="icon" />
                    Delete
                  </button>
                  <button onClick={(e) => handleNavigation('/Edit', e)}>
                    <FaEdit className="icon" />
                    Edit
                  </button>
                </div>
              </article>
            ))}
          </div>
        </div>
        <footer>
          <p></p>
        </footer>
      </div>
    </>
  );
}
