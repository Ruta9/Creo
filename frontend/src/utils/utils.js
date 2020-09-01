
export const randomColor = () => {
    const colors = [
        'red',
        'orange',
        'olive',
        'green',
        'teal',
        'blue',
        'violet',
        'purple',
        'pink'
    ];

    return colors[Math.floor(Math.random() * (colors.length))];
}

export const randomProjectPicture = () => {
    const pictures = [
        'apple',
        'banana',
        'chestnut',
        'grapes',
        'oranges',
        'papaja',
        'pears',
        'pineapple',
        'plumps'
    ];

    return pictures[Math.floor(Math.random() * (pictures.length))];
}
